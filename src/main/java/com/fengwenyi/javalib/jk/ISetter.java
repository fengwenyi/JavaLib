package com.fengwenyi.javalib.jk;

import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * setter方法接口定义
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-11
 */
public interface ISetter <T, U> extends BiConsumer<T, U>, Serializable {
}
