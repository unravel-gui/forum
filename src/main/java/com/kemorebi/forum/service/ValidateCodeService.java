package com.kemorebi.forum.service;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码服务
 * */
public interface ValidateCodeService {
    /**
     * 生成验证码
     * @param key 验证码uuid
     * @param response HttpServletResponse
     *
     * */
    void create(String key, HttpServletResponse response) throws IOException;
    /**
     * 校验验证码
     * @param key 前端给的验证码 key
     * @param value 前端给的验证码 value
     * */
    boolean check(String key, String value);
}
