package com.kemorebi.forum.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.util.AntPathMatcher;
/**
 * 忽略token 配置类
 */
public class IgnoreTokenConfig {
    public static final List<String> LIST = Arrays.asList(
            "/error",
            "/actuator/**",
            "/gate/**",
            "/admin/login",
//            "/api/index/**",
            "/static/**",
            "/anno/**",
            "/**/anno/**",
            "/**/swagger-ui.html",
            "/**/doc.html",
            "/**/webjars/**",
            "/**/v2/api-docs/**",
            "/**/v2/api-docs-ext/**",
            "/**/swagger-ui/*",
            "/**/v3/api-docs/**",
            "/**/v3/api-docs-ext/**",
            "/**/swagger-resources/**",
            "/api/**"
    );

    public static final List<String> ADMINLIST = Arrays.asList(
            "/admin/**"
    );
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    public static boolean isIgnoreToken(String currentUri) {
        return isIgnore(LIST, currentUri);
    }
    public static boolean isAdminToken(String currentUri) {
        return isIgnore(ADMINLIST, currentUri);
    }

    public static boolean isIgnore(List<String> list, String currentUri) {
        if (list.isEmpty()) {
            return false;
        }
        return list.stream().anyMatch((url) ->
                currentUri.startsWith(url) || ANT_PATH_MATCHER.match(url, currentUri)
        );
    }
}