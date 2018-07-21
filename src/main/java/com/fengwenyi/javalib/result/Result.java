package com.fengwenyi.javalib.result;

/**
 * 返回码封装类
 * @author Wenyi Feng.
 */
public class Result {

    /** 返回码 */
    private Integer code;

    /** 返回码说明 */
    private String msg;

    /** 数据 */
    private Object data;

    /**
     * 实例化
     */
    public Result() {
    }

    /**
     * 设置返回码
     * @param iReturnCode 自定义枚举类，需要实现或继承IReturnCode
     */
    public void setResult(IReturnCode iReturnCode) {
        common(iReturnCode);
    }

    /**
     * 添加数据
     * @param iReturnCode 自定义枚举类，需要实现或继承IReturnCode
     * @param data 自定义数据对象
     */
    public void setResult(IReturnCode iReturnCode, Object data) {
        common(iReturnCode);

        this.data = data;
    }

    /**
     * to string
     * @return String
     */
    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /** getter and setter */
    /**
     * get code
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * set code
     * @param code Integer
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * get msg
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * set msg
     * @param msg String
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * get data
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * set data
     * @param data Object
     */
    public void setData(Object data) {
        this.data = data;
    }


    // [common]
    private void common(IReturnCode iReturnCode) {
        this.code = iReturnCode.getCode();
        this.msg = iReturnCode.getMsg();
    }
}
