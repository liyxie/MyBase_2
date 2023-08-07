package com.liy.admin.controller;

import com.liy.common.api.ResultCode;
import com.liy.common.domain.AjaxResult;
import com.liy.common.domain.Page;
import com.liy.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author LiY
 * 通用Controller
 */

//@Slf4j
public class BaseController {

    /**
     * @description: 返回成功
     * @author: liy
     * @param:
     * @return:  ajaxResult
     **/
    public AjaxResult toSuccess(){
        return AjaxResult.success();
    }

    public AjaxResult toSuccess(String msg){
        return AjaxResult.success(msg);
    }

    public AjaxResult toSuccess(int code, String msg){
        return AjaxResult.success(code, msg);
    }

    public AjaxResult toError(){
        return AjaxResult.error();
    }

    public AjaxResult toError(String msg){
        return AjaxResult.error(msg);
    }

    /**
     * @description: 自动判断影响行数返回结果
     * @author: liy
     * @param:  Integer
     * @return:  AjaxResult
     **/
    public AjaxResult toAjaxResult(Integer rows){
        return rows > 0 ? toSuccess() : toError();
    }

    /**
     * @description: 自动判断true/falser返回结果
     * @author: liy
     * @param:  boolean
     * @return:  AjaxResult
     **/
    public AjaxResult toAjaxResult(boolean result){
        return result ? toSuccess() : toError();
    }

    public AjaxResult toAjaxResult(Object data){
        return StringUtils.isNotNull(data) ? toSuccess() : toSuccess(ResultCode.NO_CONTENT.getCode(),ResultCode.NO_CONTENT.getMessage());
    }

    public AjaxResult toAjaxResult(Page<?> page){
        return toAjaxResult(page.getRecords());
    }
}
