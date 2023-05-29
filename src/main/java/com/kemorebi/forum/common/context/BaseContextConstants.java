package com.kemorebi.forum.common.context;

/**
 * 常量工具类
 *
 */
public class BaseContextConstants {
    /**
     * token
     */
    public static final String TOKEN_NAME = "Authorization";
    /**
     *  用户ID
     */
    public static final String JWT_KEY_USER_ID = "userid";
    /**
     * 用户账号
     */
    public static final String JWT_KEY_ACCOUNT = "account";

    /**
     * 手机号
     */
    public static final String JWT_KEY_MOBILE= "mobile";
    /**
     * 管理员
     */
    public static final String JWT_KEY_ADMIN = "admin";

    /**
     * 动态数据库名前缀。  每个项目配置死的
     */
    public static final String DATABASE_NAME = "database_name";
}
