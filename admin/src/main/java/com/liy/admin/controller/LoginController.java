package com.liy.admin.controller;

import com.liy.common.domain.AjaxResult;
import com.liy.common.domain.dto.LoginUserDto;
import com.liy.common.domain.vo.UserVo;
import com.liy.common.util.Base64;
import com.liy.system.service.LoginService;
import com.liy.system.service.RoleService;
import com.liy.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.liy.common.util.uuidUtil;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;


/**
 * @Author LiY
 * 用户登录接口
 */

@RestController
@RequestMapping("/sys")
@Tag(name = "系统登录接口")
@Slf4j
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * @description: 获取验证码图片
     * @author: liy
     * @param: null
     * @return:  AjaxResult
     **/
    @Operation(summary = "获得验证码", description = "验证码以Base64格式图片传回，uuid为该验证码唯一标识")
    @GetMapping("/captchaImg")
    public AjaxResult captchaImg(){
        AjaxResult ajaxResult = toSuccess();

        //随机id
        String uuid = uuidUtil.randomUUID();

        //获取图片
        BufferedImage image = loginService.getCaptchaImg(uuid);

        //图片类型转换
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }
        String img_Base64 = Base64.encode(os.toByteArray());

        ajaxResult.put("img", img_Base64);
        ajaxResult.put("uuid",uuid);
        return ajaxResult;
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "登录成功返回token")
    public AjaxResult login(@Validated @RequestBody LoginUserDto loginUserDto){
        log.info("loginUserDto:   "+loginUserDto.toString());

        String token = loginService.login(loginUserDto.getUserName(), loginUserDto.getPassword(), loginUserDto.getUuid(), loginUserDto.getCode());
        log.info(token);
        return toSuccess().put("token",token);
    }

    @GetMapping("/info")
    @Operation(summary = "登录成功返回用户信息")
    public AjaxResult info(){

        // 用户信息
        UserVo userVo = userService.getUserInfo();
        AjaxResult result = toAjaxResult("userInfo", userVo);
        // 角色信息
        Set<String> roles = roleService.getRoleByUser(userVo);
        // 权限信息




        return result;
    }

    @GetMapping("/routers")
    @Operation(summary = "获得路由信息")
    public AjaxResult routers(){

        return toSuccess();
    }
}
