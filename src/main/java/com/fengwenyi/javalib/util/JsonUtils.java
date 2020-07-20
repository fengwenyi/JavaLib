package com.fengwenyi.javalib.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.Map;

/**
 * Json工具类
 * @author Erwin Feng
 * @since 2020/6/16
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换成JSON字符串
     * @param object 待转换的对象
     * @param <T> 对象的类型
     * @return JSON字符串
     */
    public static <T> String toJsonString(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换成集合
     * @param json 待转换的JSON字符串
     * @param collectionClass 运行时的集合对象
     * @param <T> 对象的类型
     * @return 返回一个集合
     */
    public static <T> Collection<T> toCollection(String json, Class<Collection<T>> collectionClass) {
        try {
            return objectMapper.readValue(json, collectionClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换成Map
     * @param json 待转换的JSON字符串
     * @param mapClass 运行时的Map对象
     * @param <T> 对象的类型
     * @return 返回一个Map
     */
    public static <T> Map<String, T> toMap(String json, Class<Map<String, T>> mapClass) {
        try {
            return objectMapper.readValue(json, mapClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     * @param json 待转换的JSON字符串
     * @param clazz 运行时的类对象
     * @param <T> 对象的类型
     * @return 返回一个对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
