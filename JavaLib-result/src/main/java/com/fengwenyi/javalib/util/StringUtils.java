package com.fengwenyi.javalib.util;

/**
 * 字符串工具类
 * @author Erwin Feng
 * @since 2019-03-25 04:24
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str 待判断的字符串
     * @return 是否是空字符串，true：空字符串；false：非空字符串
     */
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        if ("".equals(str.trim()))
            return true;
        return str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     * @param str 待判断的字符串
     * @return 是否是空字符串，true：非空字符串；false：空字符串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
