package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * XML转换工具类
 * @author Erwin Feng
 * @since 2020/8/13
 */
public class XmlUtils {

    private static final XmlMapper mapper = new XmlMapper();
    
    static {
        mapper();
    }


    private static void mapper() {
        // Include.NON_NULL 属性为NULL 不序列化
        //ALWAYS // 默认策略，任何情况都执行序列化
        //NON_EMPTY // null、集合数组等没有内容、空字符串等，都不会被序列化
        //NON_DEFAULT // 如果字段是默认值，就不会被序列化
        //NON_ABSENT // null的不会序列化，但如果类型是AtomicReference，依然会被序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //允许字段名没有引号（可以进一步减小json体积）：
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //允许单引号：
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 允许出现特殊字符和转义符
        //mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);这个已经过时。
//        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        //允许C和C++样式注释：
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        //序列化结果格式化，美化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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
    }

    /**
     * 将对象转换成XML格式的字符串
     * @param value 待转换的对象
     * @param <T> 对象的类型
     * @return 返回转换后的XML格式的字符串
     */
    public static <T> String convertString(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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
        try {
            return mapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将XML格式的字符串转换成对象
     * @param value 待转换的XML格式的字符串
     * @param valueType {@link TypeReference}
     * @param <T> 对象的类型
     * @return 返回转换后的对象
     */
    public static <T> T convertObject(String value, TypeReference<T> valueType) {
        try {
            return mapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
