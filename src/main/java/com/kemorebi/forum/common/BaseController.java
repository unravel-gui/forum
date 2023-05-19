package com.kemorebi.forum.common;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kemorebi.forum.common.R;
import com.kemorebi.forum.common.context.BaseContextHandler;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.BaseExceptionCode;


/**
 * SuperController
 *
 */
public abstract class BaseController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    /**
     * 当前页
     */
    protected static final String CURRENT = "pageNum";
    /**
     * 每页显示条数
     */
    protected static final String SIZE = "pageSize";

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public <T> R<T> success(T data) {
        return R.success(data);
    }
    public <T> R<T> success(T data, String msg) {
        return R.success(data, msg);
    }
    public R<Boolean> success() {
        return R.success();
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> R<T> fail(String msg) {
        return R.fail(msg);
    }

    public <T> R<T> fail(String msg, Object... args) {
        return R.fail(msg, args);
    }

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    public <T> R<T> fail(int code, String msg) {
        return R.fail(code, msg);
    }

    public <T> R<T> fail(BaseExceptionCode exceptionCode) {
        return R.fail(exceptionCode);
    }

    public <T> R<T> fail(BizException exception) {
        return R.fail(exception);
    }

    public <T> R<T> fail(Throwable throwable) {
        return R.fail(throwable);
    }

    public <T> R<T> validFail(String msg) {
        return R.validFail(msg);
    }

    public <T> R<T> validFail(String msg, Object... args) {
        return R.validFail(msg, args);
    }

    public <T> R<T> validFail(BaseExceptionCode exceptionCode) {
        return R.validFail(exceptionCode);
    }

    /**
     * 获取当前用户id
     */
    protected Long getUserId() {
        return BaseContextHandler.getUserId();
    }

    protected String getAccount() {
        return BaseContextHandler.getAccount();
    }

    protected Boolean getAdmin() {
        return BaseContextHandler.getAdmin();
    }



}
