package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


/**
 * XML转换工具类
 *
 * @author Erwin Feng
 * @since 2020/8/13
 */
public class XmlUtils {

    private static final XmlMapper mapper = new XmlMapper();

    static {
        configure(mapper);
    }

    public static void configure(XmlMapper mapper) {

        //枚举输出成字符串
        //WRITE_ENUMS_USING_INDEX：输出索引
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        //空对象不要抛出异常：
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        //Date、Calendar等序列化为时间格式的字符串(如果不执行以下设置，就会序列化成时间戳格式)：
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //反序列化时，遇到未知属性不要抛出异常：
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //反序列化时，遇到忽略属性不要抛出异常：
        mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

        //反序列化时，空字符串对于的实例属性为null：
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 采用字段，不使用 Getter
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);

        // jackson xml
        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
        mapper.registerModule(jacksonXmlModule);

        // java.time
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
    }

    /**
     * 将对象转换成XML格式的字符串
     *
     * @param value 待转换的对象
     * @param <T>   对象的类型
     * @return 返回转换后的XML格式的字符串
     */
    public static <T> String string(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            // PrintUtils.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将XML格式的字符串转换成对象
     *
     * @param value     待转换的XML格式的字符串
     * @param valueType 转换后的对象的class
     * @param <T>       对象的类型
     * @return 返回转换后的对象
     */
    public static <T> T object(String value, Class<T> valueType) {
        try {
            return mapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            // PrintUtils.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将XML格式的字符串转换成对象
     *
     * @param value     待转换的XML格式的字符串
     * @param valueType {@link TypeReference}
     * @param <T>       对象的类型
     * @return 返回转换后的对象
     */
    public static <T> T object(String value, TypeReference<T> valueType) {
        try {
            return mapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            // PrintUtils.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
