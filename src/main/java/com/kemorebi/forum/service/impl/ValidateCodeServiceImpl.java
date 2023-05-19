package com.kemorebi.forum.service.impl;

import com.kemorebi.forum.common.CacheKey;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.service.ValidateCodeService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    // 二级缓存操作对象
    @Autowired
    private CacheChannel cacheChannel;

    @Override
    public void create(String key, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(key)) {
            throw BizException.validFail("验证吗key不能为空");
        }
        // 计算类型验证码
        setHeader(response, "arithmetic");

        Captcha captcha = createCaptcha("arithmetic");
        cacheChannel.set(CacheKey.CAPTCHA, key, StringUtils.lowerCase(captcha.text()));
        // 输出验证码
        captcha.out(response.getOutputStream());
    }

    @Override
    public boolean check(String key, String value) {
        if (StringUtils.isBlank(value)) {
            throw BizException.validFail("请输入验证码");
        }
        CacheObject cacheObject = cacheChannel.get(CacheKey.CAPTCHA, key);
        if (cacheObject.getValue() == null) {
            throw BizException.validFail("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(cacheObject.getValue()))) {
            throw BizException.validFail("验证码不正确");
        }
        // 验证通过，删除验证码缓存
        cacheChannel.evict(CacheKey.CAPTCHA, key);
        return true;
    }

    private Captcha createCaptcha(String type) {
        Captcha captcha = null;
        // 根据类型进行生成
        if (StringUtils.equalsIgnoreCase(type, "gif")) {
            captcha = new GifCaptcha(115, 42, 4);
        } else if (StringUtils.equalsIgnoreCase(type, "png")) {
            captcha = new SpecCaptcha(115, 42, 4);
        } else if (StringUtils.equalsIgnoreCase(type, "arithmetic")) {
            captcha = new ArithmeticCaptcha(115, 42);
        } else if (StringUtils.equalsIgnoreCase(type, "chinese")) {
            captcha = new ChineseCaptcha(115, 42);
        }
        captcha.setCharType(2);
        return captcha;
    }

    /**
     * 设置请求头信息
     * */
    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, "gif")) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES,0L);
    }
}
