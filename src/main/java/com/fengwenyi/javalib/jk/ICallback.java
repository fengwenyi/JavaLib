package com.fengwenyi.javalib.jk;

/**
 * 回调接口
 * <p>
 *     这可能并不满足你的实际需求，你可以继承该接口{或是仿照}
 *     以此来完成你的需求。
 * </p>
 * @author Wenyi Feng
 */
public interface ICallback<T> {

    /**
     * 成功回调
     * @param t 泛型，你可以自定义返回对象或其他类型的数据
     */
    void onSuccess(T t);

    /**
     * 失败回调
     * @param msg 错误信息
     */
    void onFail(String msg);
}
