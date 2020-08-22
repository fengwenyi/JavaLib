package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

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
            PrintUtils.info(key + "---->" + value);
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            PrintUtils.info(key + "====>" + value);
        }
    }
}
