package com.kemorebi.forum.filters;

import com.kemorebi.forum.common.R;
import com.kemorebi.forum.common.context.BaseContextConstants;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.utils.jwt.JwtUserInfo;
import com.kemorebi.forum.utils.jwt.client.JwtTokenClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
@Configuration
public class TokenContextFilter extends BaseFilter implements Filter {
    @Autowired
    private JwtTokenClientUtils jwtTokenClientUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 判断是否直接放行
        if (isIgnoreToken(request)) {
            log.info("令牌验证成功，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 从请求头中获取前端提交的jwt令牌
        String userToken = request.getHeader(BaseContextConstants.TOKEN_NAME);

        JwtUserInfo jwtUserInfo = null;
        // 解析jwt令牌
        try{
//            if (userToken==null) {
//                errorResponse(response,ExceptionCode.JWT_ILLEGAL_ARGUMENT.getMsg(), ExceptionCode.JWT_ILLEGAL_ARGUMENT.getCode(), 401);
//            }
            jwtUserInfo = jwtTokenClientUtils.getUserInfo(userToken);
        } catch (BizException e) {
            errorResponse(response, e.getMessage(), e.getCode(), 200);
        } catch (Exception e) {
            errorResponse(response,"解析jwt令牌出错", R.FAIL_CODE, 200);
        }
        // 将信息放入header
        if (jwtUserInfo != null) {
            addUserInfo(BaseContextConstants.JWT_KEY_ACCOUNT,
                    jwtUserInfo.getAccount());
            addUserInfo(BaseContextConstants.JWT_KEY_USER_ID,
                    jwtUserInfo.getUserId());
            addUserInfo(BaseContextConstants.JWT_KEY_MOBILE,
                    jwtUserInfo.getMobile());
            addUserInfo(BaseContextConstants.JWT_KEY_ADMIN,
                    jwtUserInfo.getAdmin());
        }

        // 管理员直接放行
        if (jwtUserInfo.getAdmin()) {
            // 管理员直接放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 访问管理员路径但并不是管理员, 拦截
        if (isAdminToken(request)) {
            errorResponse(response,"权限不足", R.FAIL_CODE, 403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
