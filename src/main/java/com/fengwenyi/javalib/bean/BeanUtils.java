package com.fengwenyi.javalib.bean;

import com.fengwenyi.javalib.convert.JsonUtils;

/**
 * @author Erwin Feng
 * @since 2020/8/18
 */
public class BeanUtils {

    public static <T> T copy(Object target, T dest) {
        String s = JsonUtils.convertString(target);
        return (T) JsonUtils.convertObject(s, dest.getClass());
    }

}
