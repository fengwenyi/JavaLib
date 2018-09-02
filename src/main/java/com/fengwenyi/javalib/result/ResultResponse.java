//package com.fengwenyi.javalib.result;
//
///**
// * 返回响应工具类（失败了）
// *
// * 采用builder的设计模式为你提供操作
// *
// * @author Wenyi Feng
// * @since 2018-08-27
// */
//public class ResultResponse<T> extends DefaultResult<T> {
//
//    private ResultResponse() {
//
//    }
//
//    private ResultResponse(Builder<T> builder) {
//        this();
//        this.setCode(builder.code);
//        this.setSuccess(builder.success);
//        this.setMsg(builder.msg);
//        this.setData(builder.data);
//    }
//
//    public static class Builder<T> {
//
//        private Integer code;
//        private Boolean success;
//        private String msg;
//        private T data;
//
//        public Builder ok() {
//            this.success = true;
//            return this;
//        }
//
//        public Builder error() {
//            // 有可能你在某一处让 success = true
//            // 你后面想让success = false，
//            // 那么你就可以调用该方法
//            this.success = false;
//            return this;
//        }
//
//        public Builder code(Integer code) {
//            this.code = code;
//            return this;
//        }
//
//        public Builder msg(String msg) {
//            this.msg = msg;
//            return this;
//        }
//
//        public Builder data(T data) {
//            this.data = data;
//            return this;
//        }
//
//        public Builder status(IReturnCode iReturnCode) {
//            this.code = iReturnCode.getCode();
//            this.msg = iReturnCode.getMsg();
//            return this;
//        }
//
//        public ResultResponse<T> builder() {
//            return new ResultResponse<>(this);
//        }
//
//        public ResultResponse<T> builder(T data) {
//            ResultResponse<T> resultResponse = new ResultResponse<>(this);
//            this.data = data;
//            return resultResponse;
//        }
//
//    }
//
//}
