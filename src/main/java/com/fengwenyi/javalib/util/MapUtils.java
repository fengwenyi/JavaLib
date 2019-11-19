package com.fengwenyi.javalib.util;

import java.util.Map;

/**
 * Map工具类
 * <ul>
 *     <li>判断Map是否为空</li>
 *     <li>判断Map是否不为空</li>
 * </ul>
 * @author Wenyi Feng
 * @since 2018-11-17
 */
public class MapUtils {

    /**
     * 判断Map是否为空
     * @param map 待判断的Map
     * @return 是否为空，true：空；false：不为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        if (map == null)
            return true;

        return map.isEmpty();
    }

    /**
     * 判断Map是否不为空
     * @param map 待判断的Map
     * @return 是否不为空，true：不为空；false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

}
