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
public class DefaultResult<T> extends Result<T> {

    private Boolean success;

}
