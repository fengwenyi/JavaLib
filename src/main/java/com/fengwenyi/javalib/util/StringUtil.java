package com.fengwenyi.javalib.util;

/**
 * 字符串工具类
 * <ul>
 *     <li>字符串判空</li>
 *     <li>字符串判非空</li>
 * </ul>
 * @author Wenyi Feng
 */
public class StringUtil {

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
