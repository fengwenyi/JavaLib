package com.fengwenyi.javalib.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 帮助你方便构建一个 Map，需要注意的是，实现是用 HashMap
 *
 * <p>类型说明：key 为 String 类型，value 为 Object 类型</p>
 *
 * <p>示例：</p>
 *
 * <pre>
 *     Map&lt;String, Object&gt; map = MapHelper.init().put("key", "value").build();
 * </pre>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-09-17
 */
public class MapHelper {

    private Map<String, Object> map = null;

    private MapHelper() {

    }

    public static MapHelper init() {
        MapHelper mapHelper = new MapHelper();
        mapHelper.map = new HashMap<>();
        return mapHelper;
    }

    public MapHelper put(String key, Object value) {
        if (Objects.isNull(map)) {
            return this;
        }
        map.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        return map;
    }

}
