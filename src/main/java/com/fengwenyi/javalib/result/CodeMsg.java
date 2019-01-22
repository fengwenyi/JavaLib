package com.fengwenyi.javalib.result;

import lombok.Getter;

/**
 * 返回码及描述信息
 * @author Wenyi Feng
 * @since 2019-01-22
 */
@Getter
public class CodeMsg {

    public CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /** 返回码 */
    private Integer code;

    /** 返回码描述 */
    private String msg;

    /** 成功 */
    public static CodeMsg SUCCESS = new CodeMsg(0, "Success");

    /** 初始化失败 */
    public static CodeMsg ERROR_INIT = new CodeMsg(-1, "(Error)初始化失败");
}
