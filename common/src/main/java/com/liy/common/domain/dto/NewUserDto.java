package com.liy.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * <p>
 * 新增用户信息表
 * </p>
 *
 * @author liy
 * @since 2023-07-05
 */
@Data
@Accessors(chain = true)
@Schema(name = "UserDto，用户显示信息", description = "$!{table.comment}")
@ToString
public class NewUserDto {

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "角色ID")
    @NotNull
    private Long roleId;

    @Schema(description = "用户账号")
    @NotNull
    private String userName;

    @Schema(description = "用户密码")
    @NotNull
    private String password;

    @Schema(description = "用户昵称")
    @NotNull
    private String nickName;

    @Schema(description = "用户类型（00系统用户）")
    private String userType;

    @Schema(description = "用户邮箱")
    @NotNull
    private String email;

    @Schema(description = "手机号码")
    @NotNull
    private String phonenumber;

    @Schema(description = "用户性别（0男 1女 2未知）")
    @NotNull
    private String sex;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private Date loginDate;

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
