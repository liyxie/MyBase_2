package com.liy.common.domain.po;


/**
 * @description: 用户与角色关系对象
 * @author: liy
 **/
public class UserRolePo {

  private long userId;
  private long roleId;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

}
