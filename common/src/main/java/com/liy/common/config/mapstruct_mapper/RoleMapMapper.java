package com.liy.common.config.mapstruct_mapper;

import com.liy.common.domain.po.RolePo;
import com.liy.common.domain.vo.RoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author LiY
 * Role对象转换
 */

@Mapper
public interface RoleMapMapper {

    RoleMapMapper INSTANCE = Mappers.getMapper(RoleMapMapper.class);

    RoleVo toRoleVo(RolePo rolePo);


}
