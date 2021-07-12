package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collection;
import java.util.Map;

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
    public static <T> String convertString(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将一个对象转换成JSON格式的字符串，并对其格式化，方便查看。
     * <p>
     *     注意：如果本身就是一个JSON字符串，调用此方法，
     *     则返回的仍然是一个字符，并不会对其格式化，
     *     只会对其做转换处理
     * </p>
     * @param value 待转换并格式化的对象
     * @return 返回一个格式化的JSON格式的字符串
     */
    public static String prettyPrint(Object value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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
    public static <T> T convertObject(String content, Class<T> valueType) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将JSON字符串转换成集合
     * @param content 待转换的JSON字符串
     * @param collectionClass 集合
     * @param clazz 转换后的对象的class
     * @param <T> 转换后的对象
     * @return 返回转换后的集合对象
     */
    public static <T> T convertCollection(String content, Class<? extends Collection> collectionClass, Class<?> clazz) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        CollectionType valueType = objectMapper.getTypeFactory().constructCollectionType(collectionClass, clazz);
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将JSON字符串转换成集合
     * @param content 待转换的JSON字符串
     * @param valueTypeRef 关联的类型，{@code new TypeReference<List<String>>() {}}
     * @param <T> 转换后的对象
     * @return 返回转换后的集合对象
     */
    public static <T> T convertCollection(String content, TypeReference<T> valueTypeRef) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json格式的字符串转换成Map格式
     * @param json 待转换的json格式的字符串
     * @param kClazz Map key类型
     * @param vClazz Map value类型
     * @param <K> Map key对象
     * @param <V> Map value对象
     * @return 转换后的 {@code Map<K, V>}
     */
    public static <K, V> Map<K, V> convertMap(String json, Class<K> kClazz, Class<V> vClazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, kClazz, vClazz);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
