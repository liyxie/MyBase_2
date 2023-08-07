package com.liy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liy.common.config.mapstruct_mapper.UserMapMapper;
import com.liy.system.domain.LoginUser;
import com.liy.common.domain.dto.UserDto;
import com.liy.common.domain.po.UserPo;
import com.liy.common.util.StringUtils;
import com.liy.system.enums.UserStatus;
import com.liy.system.service.UserService;
import com.liy.system.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author LiY
 * UserDetailsService，实现Security的UserDetailsService
 * 用户登录检验
 */

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查找数据库用户
        log.info("username  : "+ username);
        LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPo::getUserName,username);
        UserPo userPo = userService.getOne(queryWrapper);

        if(StringUtils.isNull(userPo)){
            throw new RuntimeException("用户名错误");
        }
        //用户状态
        else if(UserStatus.DISABLE.getCode().equals(userPo.getStatus())){
            throw new RuntimeException("用户已停用");
        }
        else if(UserStatus.DELETED.getCode().equals(userPo.getStatus())){
            throw new RuntimeException("用户已注销");
        }

        //密码验证
//        String rawPassword = authenticationManager.
//        SecurityUtils.matchesPassword(,userPo.getPassword())

        UserDto userDto = UserMapMapper.INSTANCE.toDto(userPo);
        log.info("userDto :   "  + userDto.toString());
        return createLoginUser(userDto);
    }

    public UserDetails createLoginUser(UserDto user)
    {
//        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        return new LoginUser(user.getUserId(), user.getDeptId(), user);
    }

    /**
     * @description: 修改密码
     * @author: liy
     * @param:
     * @return:
     **/
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

}
