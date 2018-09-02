package com.fengwenyi.javalib.result;

import lombok.Data;

/**
 * 这是一个使用Result的例子，如果你觉得这不能满足你的要求，你也可以重新实现
 *
 * <ul>
 *     <li>code</li>
 *     <li>success</li>
 *     <li>msg</li>
 *     <li>data</li>
 * </ul>
 *
 * @author Wenyi Feng
 * @since 2018-08-28
 */
@Data
public class DefaultResult extends Result {

    /** 返回结果属性（成功[true]/失败[false]）标志 */
    private Boolean success;

    /**
     * DefaultResult toString
     * @return DefaultResult extends Result
     *          如果没有这个方法，你可能打印不出想要的数据
     */
    @Override
    public String toString() {
        return "DefaultResult{" +
                "code=" + this.getCode() +
                " success=" + this.getSuccess() +
                " msg=" + this.getMsg() +
                " data=" + this.getData() +
                "}";
    }
}
