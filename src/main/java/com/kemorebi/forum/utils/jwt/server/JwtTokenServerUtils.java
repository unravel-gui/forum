package com.kemorebi.forum.utils.jwt.server;

import com.kemorebi.forum.utils.jwt.JwtHelper;
import com.kemorebi.forum.utils.jwt.JwtUserInfo;
import com.kemorebi.forum.utils.jwt.Token;
import com.kemorebi.forum.utils.jwt.properties.AuthProperties;
import lombok.AllArgsConstructor;


/**
 * jwt token 工具类
 * */
@AllArgsConstructor
public class JwtTokenServerUtils {
    /**
     * 认证服务器使用
     * 生成 和 解析 token
     * */
    private AuthProperties authProperties;

    /**
     * 生成token
     * @param jwtUserInfo 用户tokenInfo
     * @param expire 有效时间
     * @return 生成的Token
     * @throws
     * */
    public Token generateUserToken(JwtUserInfo jwtUserInfo, Integer expire) {
        AuthProperties.TokenInfo userTokenInfo = authProperties.getUser();
        if (expire == null || expire<=0) {
            // 获得默认的有效时间设置
            expire = userTokenInfo.getExpire();
        }
        return JwtHelper.generateUserToken(jwtUserInfo, userTokenInfo.getPriKey(), expire);
    }

    /**
     * 解析token
     * @param token
     * */
    public JwtUserInfo getUserInfo(String token) {
        AuthProperties.TokenInfo userTokenInfo = authProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }

}
