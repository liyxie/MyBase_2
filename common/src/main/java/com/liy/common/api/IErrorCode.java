package com.liy.common.api;

/**
 * API返回码接口
 *
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    int getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
