package com.kemorebi.forum.common.context;

import com.kemorebi.forum.utils.DefaultValueHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 获得当前域中的 用户id appid 用户昵称
 * 注意: appod 通过token解析，用户id 和用户昵称必须在前端通过请求头的方法传入。否则整理找不到
 * */
public class BaseContextHandler {
    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

    /* 常用类型加了默认值处理 */
    public static void set(String key, Long value) {
        Map<String, String> map = getLocalMap();
        map.put(key, value==null?"0":String.valueOf(value));
    }

    public static void set(String key, String value) {
        Map<String, String> map =getLocalMap();
        map.put(key, value==null?"":value.toString());
    }

    public static void set(String key, Boolean value) {
        Map<String, String> map =getLocalMap();
        map.put(key, value==null?"false":value.toString());
    }

    /**
     * 取thread_local
     * */
    public static Map<String, String> getLocalMap() {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map==null) {
            map = new HashMap<>(10);
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    /**
     * 设置thread_local
     * */
    public static void setThreadMap(Map<String, String> threadMap) {
        THREAD_LOCAL.set(threadMap);
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.getOrDefault(key, "");
    }

    /**
     * 账号id
     * */
    public static Long getUserId() {
        Object value = get(BaseContextConstants.JWT_KEY_USER_ID);
        return DefaultValueHelper.longValueOf0(value);
    }

    /**
     * 账号id
     * */
    public static void setUserId(Long userId) {
        set(BaseContextConstants.JWT_KEY_USER_ID, userId);
    }

    public static void setUserId(String userId) {
        setUserId(DefaultValueHelper.longValueOf0(userId));
    }



    /**
     *  设置登录的账号
     * */
    public static void setAccount(String account) {
        set(BaseContextConstants.JWT_KEY_ACCOUNT, account);
    }

    /**
     *  获取登录的账号
     * */
    public static String getAccount() {
        Object value = get(BaseContextConstants.JWT_KEY_ACCOUNT);
        return retrunObjectValue(value);
    }

    /**
     * 获取用户token
     * */
    public static String getToken() {
        Object value = get(BaseContextConstants.TOKEN_NAME);
        return DefaultValueHelper.getObjectValue(value);
    }

    /**
     * 设置用户token
     * */
    public static void setToken(String token) {
        set(BaseContextConstants.TOKEN_NAME, token);
    }

    /**
     * 获得管理员信息
     * @return
     */
    public static Boolean getAdmin() {
        Object value = get(BaseContextConstants.JWT_KEY_ADMIN);
        return DefaultValueHelper.getBooleanValue(value);
    }

    /**
     * 获得管理员信息
     * @return
     */
    public static void setAdmin(Boolean value) {
        set(BaseContextConstants.JWT_KEY_ADMIN, value);
    }

    /**
     * 获得手机号
     * @return
     */
    public static String getMobile() {
        Object value = get(BaseContextConstants.JWT_KEY_MOBILE);
        return DefaultValueHelper.getObjectValue(value);
    }

    /**
     * 设置手机号
     * @return
     */
    public static void setMobile(String mobile) {
        set(BaseContextConstants.JWT_KEY_MOBILE, mobile);
    }



    public static String getDatabase() {
        Object value = get(BaseContextConstants.DATABASE_NAME);
        return DefaultValueHelper.getObjectValue(value);
    }

    public static void setDatabase(String val) {
        set(BaseContextConstants.DATABASE_NAME, val);
    }

    /**
     * 不为空返回字符串
     * 为空则返回 ""
     * */
    private static String retrunObjectValue(Object value) {
        return value==null? "": value.toString();
    }

    public static void remove() {
        if (THREAD_LOCAL!=null) {
            THREAD_LOCAL.remove();
        }
    }
}
