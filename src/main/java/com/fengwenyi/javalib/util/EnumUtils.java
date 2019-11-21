package com.fengwenyi.javalib.util;

/**
 * 枚举 工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/11/21 17:29
 */
public class EnumUtils {

    /**
     * 是否包含指定枚举类
     * @param e 枚举类
     * @param values 值
     * @param <T> 类型
     * @return true：包含，false：不包含
     */
    public static <T extends Enum<T>> boolean contains(T e, T ... values) {
        for (T t : values) {
            if (t == e)
                return true;
        }
        return false;
    }



}
