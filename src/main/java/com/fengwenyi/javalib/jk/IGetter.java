package com.fengwenyi.javalib.jk;

import java.io.Serializable;
import java.util.function.Function;

/**
 * getter方法接口定义
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-11
 */
@Deprecated
public interface IGetter <T, R> extends Function<T, R>, Serializable {
}
