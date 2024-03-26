package com.fengwenyi.javalib.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fengwenyi.javalib.util.PrintUtils;
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
        String jsonString = JsonUtils.convertString(map);
        PrintUtils.info(jsonString);
        String jsonStringPetty = JsonUtils.prettyPrint(map);
        PrintUtils.info(jsonStringPetty);
    }

    @Test
    public void testConvertObject() {
        String jsonString = "{\"other\":{\"myWeb\":\"https://fengwenyi.com\",\"github\":\"https://github.com/fengwenyi\"},\"like\":[\"movie\",\"game\",\"music\",\"tea\",\"travel\"],\"sex\":\"男\",\"name\":\"冯文议\",\"age\":28}";
        Map map = JsonUtils.convertObject(jsonString, Map.class);
        PrintUtils.info(map);
        ErwinEntity erwinEntity = JsonUtils.convertObject(jsonString, ErwinEntity.class);
        PrintUtils.info(erwinEntity);
    }

    @Test
    public void testConvertObjectCollection() {
        List<String> likes = Arrays.asList("movie",
                "game",
                "music",
                "tea",
                "travel"
        );
        String jsonString = JsonUtils.convertString(likes);
        List list = JsonUtils.convertObject(jsonString, List.class);
        PrintUtils.info(list);
        List<String> list1 = JsonUtils.convertCollection(jsonString, List.class, String.class);
        PrintUtils.info(list1);

        List<String> list2 = JsonUtils.convertCollection(jsonString, new TypeReference<List<String>>() {});
        PrintUtils.info(list2);
    }

    @Test
    public void testGetKeys() {
        String json = "{\"other\":{\"myWeb\":\"https://fengwenyi.com\",\"github\":\"https://github.com/fengwenyi\"},\"like\":[\"movie\",\"game\",\"music\",\"tea\",\"travel\"],\"sex\":\"男\",\"name\":\"冯文议\",\"age\":28}";
        List<String> keys = JsonUtils.getKeys(json);
        for (String key : keys) {
            System.out.println(key);
        }
    }

}
