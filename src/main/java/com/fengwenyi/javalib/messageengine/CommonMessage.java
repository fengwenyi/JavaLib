package com.fengwenyi.javalib.messageengine;

import com.fengwenyi.javalib.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用消息组件
 *
 * 消息的基类，如果该类不满足你的需求，你可以继续继承他
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public class CommonMessage {

    /** 消息头 */
    private Map<String, Object> header = new HashMap<>();

    /** 消息体 */
    private Object body;

    /**
     * 设置消息头的属性（key-value）
     * @param key map-key
     * @param value map-value
     */
    public void setHeader(String key, Object value) {
        if (StringUtils.isNotEmpty(key))
            header.put(key, value);
    }

    /**
     * 通过key获取消息头对应的值Object
     * @param key map-key
     * @return 消息头key对应的值
     *          如果key为空或是header为空，都将返回null
     */
    public Object getHeader(String key) {
        if (StringUtils.isEmpty(key)
                || header == null
                || header.isEmpty())
            return null;
        return header.get(key);
    }

    /**
     * 设置消息体
     * @param body 数据（Object）
     */
    public void setBody(Object body) {
        if (body != null)
            this.body = body;
    }

    /**
     * 获取消息体
     * @return Object data
     *         如果object没有被实例化为对象，那么将返回null
     */
    public Object getBody() {
        if (body != null)
            return body;
        return null;
    }

}
