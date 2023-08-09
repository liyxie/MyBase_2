package com.liy.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liy.common.config.mapstruct_mapper.UserMapMapper;
import com.liy.common.domain.LoginUserPoJo;
import com.liy.common.domain.po.UserPo;
import com.liy.system.domain.LoginUser;
import com.liy.system.service.UserService;
import com.liy.system.service.impl.TokenService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author LiY
 */

@SpringBootTest
public class JwtTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @Test
    public void test1(){
        LambdaQueryWrapper<UserPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPo::getUserId,"1");
        UserPo userPo = userService.getOne(wrapper);
        LoginUserPoJo poJo = UserMapMapper.INSTANCE.toLoginUserPoJo(userPo);
        LoginUser loginUser = new LoginUser(poJo.getUserId(),poJo.getDeptId(),poJo);
        String token = tokenService.createToken(loginUser);
        System.out.println(token);

        Claims claims = tokenService.parseToken(token);
        System.out.println(claims.toString());
        System.out.println(claims);
    }
}
