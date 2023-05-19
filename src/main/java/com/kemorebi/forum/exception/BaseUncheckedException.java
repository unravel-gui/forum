package com.kemorebi.forum.exception;

/**
 * 非运行期间异常基类，所有自定义非运行时异常继承该类
 * */
public class BaseUncheckedException extends RuntimeException implements BaseException {

    /**
     * 异常信息
     * */
    protected String message;

    /**
     * 具体异常码
     * */
    protected int code;

    public BaseUncheckedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    @Override
    public String getMessage(){
        return message;
    }


    @Override
    public int getCode() {
        return code;
    }
}
