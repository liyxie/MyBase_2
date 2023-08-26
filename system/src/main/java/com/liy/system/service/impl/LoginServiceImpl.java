package com.liy.system.service.impl;


import com.google.code.kaptcha.Producer;
import com.liy.common.constarnt.CacheConstants;
import com.liy.common.constarnt.Constants;
import com.liy.common.core.RedisCache;
import com.liy.system.domain.LoginUser;
import com.liy.common.util.StringUtils;
import com.liy.system.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author LiY
 * 用户登录服务
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * @description: 生成验证码图片
     * @author: liy
     * @param:  uuid
     * @return:
     **/
    @Override
    public BufferedImage getCaptchaImg(String uuid) {
        String capStr = null, code = null;
        BufferedImage image = null;

        String capText = captchaProducerMath.createText();
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        image = captchaProducerMath.createImage(capStr);

        //缓存
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        log.info("验证码capText ： "+capText);

        return image;
    }

    @Override
    public String login(String userName, String password, String uuid, String code) {
        //验证码检验

        this.validateCaptcha(uuid, code);
        log.info("验证码检验完成");
        //输入用户信息前置检验


//        用户检验
        Authentication authentication = null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        try{
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (AuthenticationException e){
            if(e instanceof UsernameNotFoundException){
                log.error("用户不存在"+e.getMessage());
            }
            else {
                log.info(e.getMessage());
            }
        }
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }

//        log.info("authentication   :  " + authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        AuthenticationContextHolder.clearContext();

        //生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        log.info("authentication:   "+loginUser.toString());

        //缓存

        return tokenService.createToken(loginUser);
    }

    /**
     * @description: 验证码检验
     * @author: liy
     * @param:  uuid，code
     * @return:
     **/
    private void validateCaptcha(String uuid, String code) {
        //获取缓存答案
        String key  = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");


        String RealCode = redisCache.getCacheObject(key);
        log.info("获取验证码缓存答案  ： "+ key + "  " + RealCode);
        //验证码过期或不存在
        if(StringUtils.isNull(RealCode)){
            throw new RuntimeException("验证码过期或不存在");
        }

        // 验证码错误
        if(!RealCode.equals(code)){
            throw new RuntimeException("验证码错误");
        }

    }
}
