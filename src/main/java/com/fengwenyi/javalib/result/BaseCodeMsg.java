package com.fengwenyi.javalib.result;

/**
 * 返回码及描述信息
 * @author Wenyi Feng
 * @since 2019-01-22
 */
public class BaseCodeMsg {

    /** 返回码 */
    private Integer code;

    /** 返回码描述 */
    private String msg;

    /**
     * 无参数构造方法
     */
    private BaseCodeMsg() {}

    /**
     * 构造方法
     * @param code
     * @param msg
     */
    private BaseCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static BaseCodeMsg app(Integer code, String msg) {
        return new BaseCodeMsg(code, msg);
    }

    /**
     * 返回码填充
     * @param args 填充内容
     * @return CodeMsgEnum
     */
    public BaseCodeMsg fillArgs(Object... args) {

        this.msg = String.format(this.msg, args);

        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /** 成功 */
    public static final BaseCodeMsg SUCCESS = BaseCodeMsg.app(0, "Success");

    /** 失败 */
    public static final BaseCodeMsg ERROR_INIT = BaseCodeMsg.app(-1, "(Error)初始化失败");
}
