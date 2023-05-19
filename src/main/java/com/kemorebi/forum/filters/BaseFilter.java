package com.kemorebi.forum.filters;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.kemorebi.forum.common.R;
import com.kemorebi.forum.common.context.BaseContextHandler;
import com.kemorebi.forum.config.IgnoreTokenConfig;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseFilter{
    /**
     * 判断当前uri是否需要忽略(直接放行)
     * @return
     */
    protected boolean isIgnoreToken(HttpServletRequest request) {
        // 动态获取当前请求的uri
        String uri = request.getRequestURI();
        boolean ignoreToken = IgnoreTokenConfig.isIgnoreToken(uri);
        return ignoreToken;
    }

    /**
     * 判断是否只有管理员才能访问
     * @param request
     * @return
     */
    protected boolean isAdminToken(HttpServletRequest request) {
        // 动态获取当前请求的uri
        String uri = request.getRequestURI();
        boolean ignoreToken = IgnoreTokenConfig.isAdminToken(uri);
        return ignoreToken;
    }

    protected void errorResponse(HttpServletResponse response, String errMsg, int errCode, int httpStatusCode) throws IOException {
        // 设置响应码
        response.setStatus(httpStatusCode);
        // 设置响应头
        response.setHeader("Content-Type","application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(R.fail(errCode, errMsg)));
    }

    protected void addUserInfo(String key, Object value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        BaseContextHandler.set(key, value.toString());
    }
}
