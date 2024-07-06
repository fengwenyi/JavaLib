package com.fengwenyi.javalib;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Erwin Feng
 * @since 2019-06-27 00:21
 */
public class MapTests {

    @Test
    public void ergodic() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Zhangsan");
        map.put("address", "China");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            System.out.println(key + "---->" + value);
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "====>" + value);
        }
    }
}
