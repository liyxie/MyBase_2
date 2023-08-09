package com.liy.system.domain;


import com.alibaba.fastjson2.annotation.JSONField;
import com.liy.common.domain.LoginUserPoJo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;
import com.liy.common.domain.dto.UserDto;

/**
 * @Author LiY
 * 登录用户信息记录实体，Security与Redis使用
 */
@Data
public class LoginUser implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private LoginUserPoJo user;

    public LoginUser()
    {
    }

    public LoginUser(LoginUserPoJo user, Set<String> permissions)
    {
        this.user = user;
        this.permissions = permissions;
    }

    public LoginUser(Long userId, Long deptId, LoginUserPoJo user)
    {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
    }

    public LoginUser(Long userId, Long deptId, LoginUserPoJo user, Set<String> permissions)
    {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * @description: 权限
     * @author: liy
     * @param:
     * @return:
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * @description: 账户是否未过期,过期无法验证
     * @author: liy
     * @param:
     * @return:
     **/
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @description: 指定用户是否解锁,锁定的用户无法进行身份验证
     * @author: liy
     * @param:
     * @return:
     **/
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @description: 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * @author: liy
     * @param:
     * @return:
     **/
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * @description: 是否可用 ,禁用的用户不能身份验证
     * @author: liy
     * @param:
     * @return:
     **/
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
