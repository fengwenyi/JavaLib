package com.fengwenyi.javalib.result;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-08-28
 */
public class ResultResponseUtil<T> extends DefaultResult<T> {

    public static ResultResponseUtil ok() {
        ResultResponseUtil resultResponseUtil = new ResultResponseUtil<>();
        resultResponseUtil.code(0);
        resultResponseUtil.setSuccess(true);
        return resultResponseUtil;
    }

    public static ResultResponseUtil error() {
        ResultResponseUtil resultResponseUtil = new ResultResponseUtil<>();
        resultResponseUtil.code(-1);
        resultResponseUtil.setSuccess(false);
        return resultResponseUtil;
    }

    public ResultResponseUtil code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultResponseUtil msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ResultResponseUtil data(T data) {
        this.setData(data);
        return this;
    }

    public ResultResponseUtil build(T data) {
        this.setData(data);
        return this;
    }

}
