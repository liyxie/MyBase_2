package com.liy.system.config.security_cinfig;

import com.alibaba.fastjson2.JSON;
import com.liy.common.api.ResultCode;
import com.liy.common.domain.AjaxResult;
import com.liy.common.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.Serial;
import java.io.Serializable;

/**
 * @Author LiY
 * 认证失败处理
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    @Serial
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
    {
        int code = ResultCode.UNAUTHORIZED.getCode();
//        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        String msg = "请求访问：{"+ request.getRequestURI() +"}，认证失败，无法访问系统资源";
        // 返回客户端错误
        WebUtil.renderString(response, JSON.toJSONString(AjaxResult.error(ResultCode.UNAUTHORIZED.getCode(), msg)));
    }
}
