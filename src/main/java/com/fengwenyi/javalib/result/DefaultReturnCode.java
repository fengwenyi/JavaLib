package com.fengwenyi.javalib.result;

import lombok.Getter;

/**
 * <p>
 *     默认返回码
 * </p>
 *
 * <p>
 *     这是一个使用IReturnCode写的示例，也是提供默认使用的
 * </p>
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
@Getter
public enum DefaultReturnCode implements IReturnCode {

    /** 初始化 */
    ERROR_INIT(-1, "init..."),

    /** 系统内部错误 */
    ERROR_500(500, "(Error)程序出错"),

    /** 成功 */
    SUCCESS(0, "Success")
    ;

    /** 返回码 */
    private Integer code;
    /** 描述 */
    private String msg;

    /**
     * 构造方法
     * @param code 返回码
     * @param msg 描述
     */
    DefaultReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
