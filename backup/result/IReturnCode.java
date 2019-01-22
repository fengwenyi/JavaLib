package com.fengwenyi.javalib.result;

/**
 * 返回码接口
 * @author Wenyi Feng.
 */
public interface IReturnCode {

    /**
     * @return 返回码
     */
    Integer getCode();

    /**
     * @return 返回码描述信息
     */
    String getMsg();

}
