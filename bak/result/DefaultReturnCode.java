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

    //0
    SUCCESS(0, "Success."),

    // 系统错误
    ERROR_500(500, "(Error)程序出错。"),
    ERROR_INIT(-1, "(Error)数据初始化。"),
    ERROR_UNKNOWN(1000, "(Error)未知错误。"),

    // 参数
    ERROR_PARAM_NULL(1101, "(Error)参数不能为空。"),
    ERROR_PARAM_REQUIRE_NULL(1102, "(Error)参数必填项不能为空。"),
    ERROR_PARAM_WRONGFUL(1103, "(Error)参数不合法。"),

    // 数据库
    ERROR_DB_SAVE_FAIL(1201, "(Error)数据库保存数据失败。"),

    // token
    ERROR_TOKEN_NULL(1601, "(Error)token不能为空。"),
    ERROR_TOKEN_WRONGFUL(1602, "(Error)token不合法。"),
    ERROR_TOKEN_INCORRECT(1603, "(Error)token不匹配。"),

    // 密钥
    ERROR_KEY_NULL(1701, "(Error)密钥不能为空。"),
    ERROR_KEY_AUTH_FAIL(1702, "(Error)密钥认证失败。")
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
