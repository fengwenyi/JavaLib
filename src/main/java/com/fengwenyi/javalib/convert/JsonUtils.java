package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON转换工具类
 * @author Erwin Feng
 * @since 2020/6/16
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换成JSON字符串
     * @param value 待转换的对象
     * @param <T> 对象的类型
     * @return JSON字符串
     */
    public static <T> String convertJson(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     * @param content 待转换的JSON字符串
     * @param valueType 运行时的类对象
     * @param <T> 对象的类型
     * @return 返回一个对象
     */
    public static <T> T convertJson(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
