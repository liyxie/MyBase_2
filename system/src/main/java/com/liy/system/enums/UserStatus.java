package com.liy.system.enums;

/**
 * @Author LiY
 * 用户状态
 */
public enum UserStatus {
    OK("正常", "0"),
    DISABLE("停用","1"),
    DELETED( "删除","2");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
