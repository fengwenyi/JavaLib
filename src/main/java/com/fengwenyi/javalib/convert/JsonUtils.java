package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fengwenyi.javalib.exception.ExceptionUtils;
import com.fengwenyi.javalib.util.StrUtils;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * JSON转换工具类
 *
 * @author Erwin Feng
 * @since 2020/6/16
 */
public class JsonUtils {

    private static final JsonMapper mapper = new JsonMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    static {
        configure(mapper);
    }

    public static void configure(JsonMapper mapper) {
        // Include.NON_NULL 属性为NULL 不序列化
        //ALWAYS // 默认策略，任何情况都执行序列化
        //NON_EMPTY // null、集合数组等没有内容、空字符串等，都不会被序列化
        //NON_DEFAULT // 如果字段是默认值，就不会被序列化
        //NON_ABSENT // null的不会序列化，但如果类型是AtomicReference，依然会被序列化
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //允许字段名没有引号（可以进一步减小json体积）：
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //允许单引号：
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 允许出现特殊字符和转义符
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        //允许C和C++样式注释：
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

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

        // java.time 设置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
    }

    /**
     * 将对象转换成JSON字符串
     *
     * @param value 待转换的对象
     * @param <T>   对象的类型
     * @return JSON字符串
     */
    public static <T> String string(T value) {
        if (Objects.isNull(value)) {
            return StrUtils.BLANK;
        }
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("object convert json failed, object: {}, exception: {}",
                    value, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将一个对象转换成JSON格式的字符串，并对其格式化，方便查看。
     * <p>
     * 注意：如果本身就是一个JSON字符串，调用此方法，
     * 则返回的仍然是一个字符，并不会对其格式化，
     * 只会对其做转换处理
     * </p>
     *
     * @param value 待转换并格式化的对象
     * @return 返回一个格式化的JSON格式的字符串
     */
    public static String pretty(Object value) {
        if (Objects.isNull(value)) {
            return StrUtils.BLANK;
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("object convert json failed, object: {}, exception: {}",
                    value, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     *
     * @param content   待转换的JSON字符串
     * @param valueType 运行时的类对象
     * @param <T>       对象的类型
     * @return 返回一个对象
     */
    public static <T> T object(String content, Class<T> valueType) {
        if (StrUtils.isBlank(content) || Objects.isNull(valueType)) {
            return null;
        }
        try {
            return mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error("json convert object failed, json: {}, exception: {}",
                    content, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     *
     * @param content   待转换的JSON字符串
     * @param valueType {@link TypeReference}
     * @param <T>       对象的类型
     * @return 返回一个对象
     */
    public static <T> T object(String content, TypeReference<T> valueType) {
        if (StrUtils.isBlank(content) || Objects.isNull(valueType)) {
            return null;
        }
        try {
            return mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error("json convert object failed, json: {}, exception: {}",
                    content, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将JSON字符串转换成集合
     *
     * @param content         待转换的JSON字符串
     * @param collectionClass 集合
     * @param clazz           转换后的对象的class
     * @param <T>             转换后的对象
     * @return 返回转换后的集合对象
     */
    public static <T> T collection(String content, Class<? extends Collection> collectionClass, Class<?> clazz) {
        if (StrUtils.isBlank(content) || Objects.isNull(collectionClass) || Objects.isNull(clazz)) {
            return null;
        }
        try {
            CollectionType valueType = mapper.getTypeFactory().constructCollectionType(collectionClass, clazz);
            return mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error("json convert collection failed, json: {}, exception: {}",
                    content, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将JSON字符串转换成集合
     *
     * @param content      待转换的JSON字符串
     * @param valueTypeRef 关联的类型，{@code new TypeReference<List<String>>() {}}
     * @param <T>          转换后的对象
     * @return 返回转换后的集合对象
     */
    public static <T> T collection(String content, TypeReference<T> valueTypeRef) {
        if (StrUtils.isBlank(content) || Objects.isNull(valueTypeRef)) {
            return null;
        }
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error("json convert collection failed, json: {}, exception: {}",
                    content, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 将json格式的字符串转换成Map格式
     *
     * @param json   待转换的json格式的字符串
     * @param kClazz Map key类型
     * @param vClazz Map value类型
     * @param <K>    Map key对象
     * @param <V>    Map value对象
     * @return 转换后的 {@code Map<K, V>}
     */
    public static <K, V> Map<K, V> map(String json, Class<K> kClazz, Class<V> vClazz) {
        if (StrUtils.isBlank(json) || Objects.isNull(kClazz) || Objects.isNull(vClazz)) {
            return null;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, kClazz, vClazz);
            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            log.error("json convert map failed, json: {}, exception: {}",
                    json, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static <K, V> Map<K, V> map(String json) {
        if (StrUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, new TypeReference<Map<K, V>>() {});
        } catch (JsonProcessingException e) {
            log.error("json convert map failed, json: {}, exception: {}",
                    json, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 获取 json 字符串的 key
     *
     * <p>
     * 只获取 json 第一层的 key
     * </p>
     *
     * @param content JSON字符串
     * @return key列表
     */
    public static List<String> getKeys(String content) {
        if (StrUtils.isBlank(content)) {
            return Collections.emptyList();
        }
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(content);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (Objects.isNull(jsonNode)) {
            return Collections.emptyList();
        }
        Iterator<String> iterator = jsonNode.fieldNames();
        List<String> keys = new ArrayList<>();
        while (iterator.hasNext()) {
            keys.add(iterator.next());
        }
        return keys;
    }

}
