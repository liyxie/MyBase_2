package com.liy.common.api;

/**
 * API返回码封装类
 * @Authon liy
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    NO_CONTENT(204, "请求成功但没有数据"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    WARN(601, "系统警告");
//    UNAUTHORIZED(401, "没有相关权限");


    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
