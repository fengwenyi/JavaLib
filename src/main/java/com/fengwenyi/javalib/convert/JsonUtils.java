package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final DateTimeFormatter dateTimeFormatter = FormatterUtils.dateTimeFormatter();
    private static final DateTimeFormatter dateFormatter = FormatterUtils.dateFormatter();
    private static final DateTimeFormatter timeFormatter = FormatterUtils.timeFormatter();

    static {
        javaTimeModel();
        mapper();
    }

    private static void javaTimeModel() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        mapper.registerModule(javaTimeModule);
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
     * 将对象转换成JSON字符串
     * @param value 待转换的对象
     * @param <T> 对象的类型
     * @return JSON字符串
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
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
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
        try {
            return mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     * @param content 待转换的JSON字符串
     * @param valueType {@link TypeReference}
     * @param <T> 对象的类型
     * @return 返回一个对象
     */
    public static <T> T convertObject(String content, TypeReference<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
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
        CollectionType valueType = mapper.getTypeFactory().constructCollectionType(collectionClass, clazz);
        try {
            return mapper.readValue(content, valueType);
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
        try {
            return mapper.readValue(content, valueTypeRef);
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
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, kClazz, vClazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
