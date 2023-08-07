package com.liy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liy.common.domain.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author liy
 * @since 2023-07-05
 */

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

    /**
     * @author LiY
     */

    UserPo getOneUserById(@Param("user_id") Long id);


}
