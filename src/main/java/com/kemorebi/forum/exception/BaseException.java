package com.kemorebi.forum.exception;

public interface BaseException {
    /**
     * 统一参数验证异异常码
     * */
    int BASE_VALID_PARAM = -9;

    /**
     * 返回异常信息
     * */
    String getMessage();

    /**
     * 返回异常编码
     * */
    int getCode();
}
