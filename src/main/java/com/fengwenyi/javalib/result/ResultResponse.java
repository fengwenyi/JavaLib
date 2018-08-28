package com.fengwenyi.javalib.result;

/**
 * 返回响应工具类
 *
 * 采用builder的设计模式为你提供操作
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public class ResultResponse<T> extends DefaultResult<T> {

    private ResultResponse() {

    }

    private ResultResponse(Builder<T> builder) {
        this();
        this.setCode(builder.code);
        this.setSuccess(builder.success);
        this.setMsg(builder.msg);
        this.setData(builder.data);
    }

    public static class Builder<T> {

        private Integer code;
        private Boolean success;
        private String msg;
        private T data;

        public Builder() {
            this.code = DefaultReturnCode.ERROR_INIT.getCode();
            this.msg = DefaultReturnCode.ERROR_INIT.getMsg();
            this.success = false;
            this.data = (T) "";
        }

        public Builder(Integer code, String msg) {
            this();
            this.code = code;
            this.msg = msg;
        }

        public Builder(Integer code, String msg, T data) {
            this(code, msg);
            this.data = data;
        }

        public Builder(IReturnCode iReturnCode) {
            this(iReturnCode.getCode(), iReturnCode.getMsg());
        }

        public Builder(IReturnCode iReturnCode, T data) {
            this(iReturnCode);
            this.data = data;
        }

        public Builder ok() {
            this.success = true;
            return this;
        }

        public Builder error() {
            // 有可能你在某一处让 success = true
            // 你后面想让success = false，
            // 那么你就可以调用该方法
            this.success = false;
            return this;
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder status(IReturnCode iReturnCode) {
            this.code = iReturnCode.getCode();
            this.msg = iReturnCode.getMsg();
            return this;
        }

        public ResultResponse<T> builder() {
            ResultResponse<T> resultResponse = new ResultResponse<>(this);

            //

            return resultResponse;
        }

        public ResultResponse<T> builder(T data) {
            ResultResponse<T> resultResponse = new ResultResponse<>(this);

            this.data = data;

            //

            return resultResponse;
        }

    }

}
