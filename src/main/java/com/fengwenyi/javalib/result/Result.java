package com.fengwenyi.javalib.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 通用接口JSON格式返回实体工具类
 * 包含的属性：
 *      code 返回码
 *      msg  描述
 *      data 数据（对象或数组）
 * 用例：Result.success(T)
 *      Result.error(T)
 * @author Wenyi Feng
 * @since 2018-12-01
 */
/* 过滤掉null字段，data里面的每个对象都需要加 */
//@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    /** 返回码 */
    private Integer code;

    /** 描述 */
    private String msg;

    /** 数据 */
    private T data;

    /**
     * 返回码构造方法
     * @param code 返回码
     * @param msg 描述
     */
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回码构造方法
     * @param baseCodeMsg {@link BaseCodeMsg}
     */
    public Result(BaseCodeMsg baseCodeMsg) {
        if (baseCodeMsg != null) {
            this.code = baseCodeMsg.getCode();
            this.msg = baseCodeMsg.getMsg();
        }
    }

    /**
     * 数据构造方法
     * @param data {@link T}
     */
    public Result(T data) {
        this.data = data;
    }

    /**
     * 成功时调用
     * @param <T> {@link T}
     * @return {@link Result}
     */
    public static <T> Result<T> success() {
        return new Result<>(BaseCodeMsg.SUCCESS);
    }

    /**
     * 成功时调用
     * @param data 数据
     * @param <T> {@link T}
     * @return {@link Result}
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.data = data;
        return result;
    }

    /**
     * 错误时调用
     * @param baseCodeMsg 返回码
     * @param <T> {@link T}
     * @return {@link Result}
     */
    public static <T> Result<T> error(BaseCodeMsg baseCodeMsg) {
        return new Result<>(baseCodeMsg);
    }

    /**
     * 错误时调用
     * @param code 返回码
     * @param msg 描述
     * @param <T> {@link T}
     * @return {@link Result}
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 错误时调用，默认错误，错误码：-1；描述信息：Error
     * @param <T> {@link T}
     * @return {@link Result}
     */
    public static <T> Result<T> error() {
        return new Result<>(BaseCodeMsg.ERROR_INIT);
    }

    /**
     * 获取返回码
     * @return 返回码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取描述信息
     * @return 描述信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 获取数据
     * @return {@link T}
     */
    public T getData() {
        return data;
    }
}
