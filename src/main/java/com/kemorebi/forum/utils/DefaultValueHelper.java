package com.kemorebi.forum.utils;

import cn.hutool.core.util.StrUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.*;
import java.util.Date;
import java.util.function.Function;

public class DefaultValueHelper {

    /**
     * 布尔辅助类
     */
    public static boolean getBooleanValue(Object obj) {
        return obj == null ? false : new Boolean(obj.toString());
    }

    /**
     * 字符串辅助类
     */
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }
    public static String getOrDef(String val, String def) {
        return StrUtil.isEmpty(val) ? def : val;
    }

    /**
     * 数字辅助类
     */

    private static <T, R> R valueOfDef(T t, Function<T, R> function, R def) {
        try {
            return function.apply(t);
        } catch (Exception e) {
            return def;
        }
    }

    public static Long longValueOf0(String value) {
        return valueOfDef(value, (val) -> Long.valueOf(val), 0L);
    }

    public static Long longValueOfNil(Object value) {
        return valueOfDef(value, (val) -> Long.valueOf(val.toString()), null);
    }

    public static Long longValueOf0(Object value) {
        return valueOfDef(value, (val) -> Long.valueOf(val.toString()), 0L);
    }

    /**
     * 日期辅助类
     */

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
}
