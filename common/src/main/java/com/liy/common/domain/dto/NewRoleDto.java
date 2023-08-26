package com.liy.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 新增角色信息表
 * </p>
 *
 * @author liy
 * @since 2023-08-16
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "RoleBo", description = "$!{table.comment}")
public class NewRoleDto {

    @Schema(description = "角色名称")
    @NotNull
    private String roleName;

    @Schema(description = "角色权限字符串")
    @NotNull
    private String roleKey;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "备注")
    private String remark;

}
