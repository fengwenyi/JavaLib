package com.fengwenyi.javalib.handler;

import java.util.Map;

/**
 * handler注册器
 *
 * 你可以使用此类向handler注册message，并获取一个message，来返回一个message进行处理
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public class HandlerRegister {

    /** handler的集合 */
    private Map<String, Handler> handlers;

    /**
     * 获取handler
     * @param key 通过map-key获取handler
     * @return handler，map-value
     */
    public Handler getHandler(String key) {
        return handlers.get(key);
    }

}
