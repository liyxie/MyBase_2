package com.liy.common.config.mapstruct_mapper;

import com.liy.common.domain.LoginUserPoJo;
import com.liy.common.domain.dto.UserDto;
import com.liy.common.domain.po.UserPo;
import com.liy.common.domain.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author LiY
 * User对象转换类
 */

@Mapper
public interface UserMapMapper {

    UserMapMapper INSTANCE = Mappers.getMapper(UserMapMapper.class);

    UserVo toVo(UserPo userPo);

    UserVo toVo(LoginUserPoJo loginUserPoJo);

    UserDto toDto(UserPo userPo);

    LoginUserPoJo toLoginUserPoJo(UserPo userPo);

}
