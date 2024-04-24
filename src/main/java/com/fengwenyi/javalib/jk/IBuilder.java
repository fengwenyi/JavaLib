package com.fengwenyi.javalib.jk;

/**
 * 构造者模式接口
 *
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2.1.4
 */
@Deprecated
public interface IBuilder<T> {

    /**
     * 构建一个对象
     * @return 构建对象
     */
    T build();

}
