package com.fengwenyi.javalib.handler;

import java.util.Map;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
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
