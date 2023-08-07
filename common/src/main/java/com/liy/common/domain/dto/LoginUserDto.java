package com.liy.common.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @Author LiY
 * 登录操作对象
 */

@Data
@Schema(description = "登录用户输入信息")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserDto {

    @Schema(description = "用户账号")
    @NonNull
    @NotNull(message = "用户名不能为空")
    private String userName;

    @Schema(description = "密码")
    @NonNull
    @NotNull(message = "密码不能为空")
    private String password;

    @Schema(description = "验证码答案")
    @NonNull
    @NotNull(message = "验证码不能为空")
    private String code;

    @Schema(description = "验证码标识uuid")
    @NonNull
    @NotNull(message = "验证码信息缺失")
    private String uuid;
}
