package com.fengwenyi.javalib.helper;

import com.fengwenyi.javalib.convert.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-09-17
 */
public class MapHelperTests {

    @Test
    public void testBuild() {
        Map<String, Object> map = MapHelper.init().put("key", "value").build();
        System.out.println(JsonUtils.pretty(map));
    }

}
