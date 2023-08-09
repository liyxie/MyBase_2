package com.liy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liy.common.domain.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liy.common.config.mapstruct_mapper.UserMapMapper;
import com.liy.common.domain.dto.UserPageDto;
import com.liy.common.domain.po.UserPo;
import com.liy.common.domain.vo.UserVo;
import com.liy.common.util.StringUtils;
import com.liy.system.domain.LoginUser;
import com.liy.system.enums.UserStatus;
import com.liy.system.mapper.UserMapper;
import com.liy.system.service.UserService;
import com.liy.system.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author liy
 * @since 2023-07-05
 */

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserVo> listPageBy(UserPageDto userPageDto, Integer pageNum, Integer pageSize) {

        Page<UserPo> userPoPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UserPo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(StringUtils.isNotNull(userPageDto.getUserId()), UserPo::getUserId, userPageDto.getUserId())
                .eq(StringUtils.isNotNull(userPageDto.getUserId()), UserPo::getUserId, userPageDto.getUserId())
                .like(StringUtils.isNotNull(userPageDto.getUserName()), UserPo::getUserName, userPageDto.getUserName())
                .like(StringUtils.isNotNull(userPageDto.getNickName()), UserPo::getNickName, userPageDto.getNickName())
                .eq(StringUtils.isNotNull(userPageDto.getPhonenumber()), UserPo::getPhonenumber, userPageDto.getPhonenumber())
                .eq(StringUtils.isNotNull(userPageDto.getEmail()), UserPo::getEmail, userPageDto.getEmail())
                .eq(StringUtils.isNotNull(userPageDto.getSex()), UserPo::getSex, userPageDto.getSex())
                .eq(StringUtils.isNotNull(userPageDto.getStatus()), UserPo::getStatus, userPageDto.getStatus());
        userMapper.selectPage(userPoPage,lambdaQueryWrapper);
        log.info(userPoPage.toString());

        Page<UserVo> userVoPage = new Page<UserVo>(userPoPage);
        userVoPage.setRecords(userPoPage.getRecords().stream().map(UserMapMapper.INSTANCE::toVo).collect(Collectors.toList()));

        return userVoPage;
    }

    @Override
    public UserVo getUserInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        UserVo userVo = UserMapMapper.INSTANCE.toVo(loginUser.getUser());
        if(Objects.isNull(userVo)){
            throw new RuntimeException("用户登录信息错误");
        }
        return userVo;
    }

    @Override
    public void removeBatchByIds(Long[] ids) {
        List<Long> userIds = Arrays.asList(ids);
        //更改用户状态
        List<UserPo> userPos = userMapper.selectBatchIds(userIds);
        for (UserPo userPo:userPos) {
            userPo.setAvatar(UserStatus.DELETED.getCode());
            userMapper.updateById(userPo);
        }

    }
}
