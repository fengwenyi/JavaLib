package com.fengwenyi.javalib.handler;

import com.fengwenyi.javalib.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * handler注册器
 *
 * 你可以使用此类向handler注册message，并获取一个message，来返回一个message进行处理
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
@Slf4j
public class HandlerRegister {

    /** handler的集合 */
    private Map<String, Handler> handlers;

    /**
     * 获取handler
     * @param key handler对应的key
     * @return handler
     */
    public Handler getHandler(String key) {
        if (StringUtils.isEmpty(key) // key为空
                || handlers == null // handlers没有被实例化
                || handlers.isEmpty()) // handlers没有值
            return null;
        return handlers.get(key);
    }

    /**
     * 设置handler
     * @param key handler对应的key
     * @param handler handler
     */
    public void setHandler(String key, Handler handler) {

        // 参数判断
        if (StringUtils.isEmpty(key) // key 为空
                || handler == null) // handler 没有被实例化
            return;

        if (handlers == null)
            handlers = new HashMap<>();

        // 判断之前已指定key对应的handler，如果有，给出警告提示
        Handler handlerObj = getHandler(key);
        if (handlerObj != null)
            log.warn("{} 之前对应 {}", key, handlerObj);

        handlers.put(key, handler);
    }

}
