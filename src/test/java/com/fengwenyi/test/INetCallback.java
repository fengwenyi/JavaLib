package com.fengwenyi.test;

import com.fengwenyi.javalib.jk.ICallback;

/**
 * @author Wenyi Feng
 */
public interface INetCallback<S, T> extends ICallback<T> {

    // 加载之前
    void before();

    // 加载多少
    void loading(int progress, int max);

    // more ...
    void m(S s);
}
