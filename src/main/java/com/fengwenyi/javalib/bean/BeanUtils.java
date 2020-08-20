package com.fengwenyi.javalib.bean;

import com.fengwenyi.javalib.convert.JsonUtils;

/**
 * Bean工具类
 * @author Erwin Feng
 * @since 2020/8/18
 */
public class BeanUtils {

    /**
     * 将源对象的属性值拷贝给目标对象。
     * <p>
     *     先将源对象转换成json字符，
     *     再将json字符串转换成目标对象。
     * </p>
     * @param source 源对象
     * @param targetClazz 目标对象的class
     * @param <T> 目标对象
     * @return 返回目标对象
     */
    public static <T> T copy(Object source, Class<T> targetClazz) {
        String jsonString = JsonUtils.convertString(source);
        return JsonUtils.convertObject(jsonString, targetClazz);
    }

}
