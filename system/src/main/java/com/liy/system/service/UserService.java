package com.liy.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.common.domain.Page;
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
}
