package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Erwin Feng
 * @since 2020/8/20
 */
public class JsonUtilsTests {

    @Test
    public void testConvertString() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "冯文议");
        map.put("age", 28);
        map.put("sex", "男");
        List<String> likes = Arrays.asList("movie",
                "game",
                "music",
                "tea",
                "travel"
        );
        map.put("like", likes);
        Map<String, String> otherMap = new HashMap<>();
        otherMap.put("myWeb", "https://fengwenyi.com");
        otherMap.put("github", "https://github.com/fengwenyi");
        map.put("other", otherMap);
        map.put("birthday", LocalDate.of(1992, 2, 26));
        map.put("time", LocalTime.now());
        map.put("testTime", LocalDateTime.now());
        String jsonString = JsonUtils.string(map);
        System.out.println(jsonString);
        String jsonStringPetty = JsonUtils.pretty(map);
        System.out.println(jsonStringPetty);
    }

    @Test
    public void testConvertObject() {
        String jsonString = "{\"other\":{\"myWeb\":\"https://fengwenyi.com\",\"github\":\"https://github.com/fengwenyi\"},\"like\":[\"movie\",\"game\",\"music\",\"tea\",\"travel\"],\"sex\":\"男\",\"name\":\"冯文议\",\"age\":28}";
        Map map = JsonUtils.object(jsonString, Map.class);
        System.out.println(map);
        ErwinEntity erwinEntity = JsonUtils.object(jsonString, ErwinEntity.class);
        System.out.println(erwinEntity);
    }

    @Test
    public void testConvertObjectCollection() {
        List<String> likes = Arrays.asList("movie",
                "game",
                "music",
                "tea",
                "travel"
        );
        String jsonString = JsonUtils.string(likes);
        List list = JsonUtils.object(jsonString, List.class);
        System.out.println(list);
        List<String> list1 = JsonUtils.collection(jsonString, List.class, String.class);
        System.out.println(list1);

        List<String> list2 = JsonUtils.collection(jsonString, new TypeReference<List<String>>() {
        });
        System.out.println(list2);
    }

    @Test
    public void testGetKeys() {
        String json = "{\"other\":{\"myWeb\":\"https://fengwenyi.com\",\"github\":\"https://github.com/fengwenyi\"},\"like\":[\"movie\",\"game\",\"music\",\"tea\",\"travel\"],\"sex\":\"男\",\"name\":\"冯文议\",\"age\":28}";
        List<String> keys = JsonUtils.getKeys(json);
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void testXmlToJson() {

        try {

            String xmlString = "<person><name>John</name><age>30</age></person>";

            // XML String 转换为 JsonNode
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(xmlString.getBytes());

            // 使用 ObjectMapper 将 JsonNode 转换为 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            System.out.println(jsonString);
        } catch (Exception e) {

        }
    }

}
