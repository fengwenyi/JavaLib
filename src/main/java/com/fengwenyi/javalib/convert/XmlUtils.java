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
     * @param content
     * @param <T>
     * @return
     */
    public static <T> String convertXml(T content) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 将XML格式的字符串转换成对象
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T convertXml(String content, Class<T> valueType) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
