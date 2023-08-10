package com.liy.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.common.domain.Page;
import com.liy.common.domain.dto.UserDto;
import com.liy.common.domain.dto.UserPageDto;
import com.liy.common.domain.po.UserPo;
import com.liy.common.domain.vo.UserVo;
/**
 * @Author LiY
 * 用户服务
 */
public interface UserService extends IService<UserPo> {

    /**
     * @description: 条件分页获取用户
     * @author: liy
     * @param:
     * @return:
     **/
    Page<UserVo> listPageBy(UserPageDto userPageDto, Integer pageNum, Integer pageSize);

    /**
     * @description: 获取当前登录用户信息
     * @author: liy
     * @param:
     * @return:
     **/
    UserVo getUserInfo();

    /**
     * @description: 根据id批量删除用户
     * @author: liy
     * @param:
     * @return:
     **/
    void removeBatchByIds(Long[] ids);

    /**
     * @description: 改变用户状态
     * @author: liy
     * @param:
     * @return:
     **/
    Integer changeStatus(UserDto userDto);
    /**
     * @description: 修改用户信息
     * @author: liy
     * @param:
     * @return:
     **/
    Integer updateUserDto(UserDto userDto);
}
