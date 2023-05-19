package com.kemorebi.forum.utils.jwt.client;

import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.utils.jwt.JwtHelper;
import com.kemorebi.forum.utils.jwt.JwtUserInfo;
import com.kemorebi.forum.utils.jwt.properties.AuthProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JwtToken 客户端工具
 * */
@AllArgsConstructor
public class JwtTokenClientUtils {
    /**
     * 用于 认证服务的 客户端使用（如 网关） ， 在网关获取到token后，
     * 调用此工具类进行token 解析。
     * 客户端一般只需要解析token 即可
     */
    private AuthProperties authProperties;

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws BizException
     */
    public JwtUserInfo getUserInfo(String token) throws BizException {
        AuthProperties.TokenInfo userTokenInfo = authProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
