package com.kemorebi.forum.utils.jwt.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 认证属性
 * */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AuthProperties.PREFIX)
public class AuthProperties {

    public static final String PREFIX = "authentication";

    private TokenInfo user;

    @Data
    public static class TokenInfo {
        /**
         * 请求头名称
         */
        private String headerName;
        /**
         * 有效时间
         * */
        private Integer expire = 7200;
        /**
         * 加密服务使用
         * 私钥
         * */
        private String priKey;
        /**
         * 公钥
         * */
        private String pubKey;
    }
}
