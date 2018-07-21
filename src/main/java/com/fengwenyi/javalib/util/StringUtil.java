package com.fengwenyi.javalib.util;

/**
 * String 字符串
 * @author Wenyi Feng
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str [ellipsis]
     * @return [ellipsis]
     */
    public static boolean isNullStr(String str) {

        return str == null
                || "".equals(str)
                || str.length() < 1;

    }

}
