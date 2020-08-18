package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * XML转换工具类
 * @author Erwin Feng
 * @since 2020/8/13
 */
public class XmlUtils {

    /**
     * 将对象转换成XML格式的字符串
     * @param value 待转换的对象
     * @param <T> 对象的类型
     * @return 返回转换后的XML格式的字符串
     */
    public static <T> String convertString(T value) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 将XML格式的字符串转换成对象
     * @param value 待转换的XML格式的字符串
     * @param valueType 转换后的对象的class
     * @param <T> 对象的类型
     * @return 返回转换后的对象
     */
    public static <T> T convertObject(String value, Class<T> valueType) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
