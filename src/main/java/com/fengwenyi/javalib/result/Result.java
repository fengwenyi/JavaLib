package com.fengwenyi.javalib.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回码封装类
 * @author Wenyi Feng.
 */
@Data
// 无参数构造方法
@NoArgsConstructor
public class Result<T> {

    /** 返回码 */
    private Integer code;

    /** 返回码说明 */
    private String msg;

    /** 数据 */
//    private Object data;
    private T data;

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
    public void setResult(IReturnCode iReturnCode, T data) {
        common(iReturnCode);
        this.data = data;
    }

    // [common]
    private void common(IReturnCode iReturnCode) {
        this.code = iReturnCode.getCode();
        this.msg = iReturnCode.getMsg();
    }
}
