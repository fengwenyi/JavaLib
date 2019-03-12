package com.fengwenyi.javalib.handler;

import com.fengwenyi.javalib.messageengine.CommonMessage;

/**
 * Handler
 *
 * 这是一个公用的接口，你需要写一个class来实现这个接口中的方法，以便调用
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public interface Handler {

    /**
     * 执行
     * @param message 消息
     * @throws Exception 异常，可能会有异常，你可以抛出了，或是处理掉
     */
    void execute(CommonMessage message) throws Exception;

}
