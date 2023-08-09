package com.liy.common.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author liy
 * @since 2023-07-05
 * Security的UserDetails使用
 * 存储缓存的用户信息
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserPoJo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long deptId;

    private String userName;

    private String nickName;

    private String userType;

    private String email;

    private String phonenumber;

    private String sex;

    private String password;

    private String loginIp;

    private Date loginDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}
