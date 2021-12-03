package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

/**
 * JSON转换工具类
 * @author Erwin Feng
 * @since 2020/6/16
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final DateTimeFormatter dateTimeFormatter = FormatterUtils.dateTimeFormatter();
    private static final DateTimeFormatter dateFormatter = FormatterUtils.dateFormatter();
    private static final DateTimeFormatter timeFormatter = FormatterUtils.timeFormatter();

    static {
        javaTimeModel();
    }

    private static void javaTimeModel() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        objectMapper.registerModule(javaTimeModule);
    }

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
