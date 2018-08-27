package com.fengwenyi.javalib.result;

import lombok.Getter;

/**
 * 默认返回码
 *
 * 这是一个使用IReturnCode写的示例，也是提供默认使用的
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
@Getter
public enum DefaultReturnCode implements IReturnCode {

    // 500
    ERROR_500(500, "(Error)程序出错"),

    SUCCESS(0, "Success")
    ;

    private Integer code;
    private String msg;

    DefaultReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
