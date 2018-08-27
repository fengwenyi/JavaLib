package com.fengwenyi.javalib.messageengine;

import com.fengwenyi.javalib.util.StringUtil;
import lombok.Data;

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
@Data
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
    public void setProperty(String key, Object value) {
        if (StringUtil.isNotEmpty(key))
            header.put(key, value);
    }

    /**
     * 设置消息体
     * @param body 数据（Object）
     */
    public void setBody(Object body) {
        if (body != null)
            this.body = body;
    }

}
