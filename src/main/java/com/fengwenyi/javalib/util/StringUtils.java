package com.fengwenyi.javalib.util;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 * <ul>
 *     <li>字符串判空</li>
 *     <li>字符串判非空</li>
 * </ul>
 * @author Wenyi Feng
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


    /**
     * 自动填充。比如，待填充是：11，填充长度：10，则填充后的字符串为：0000000011
     * @param source 待填充
     * @param length 填充长度
     * @return 填充后的字符串
     */
    public static String autoFill(Integer source, Integer length) {

        if (source == null || length == null || (source + "").length() >= length)
            return source + "";

        return String.format("%0" + length + "d", source);
    }

    /**
     * 自动填充
     * <p>
     *     比如：待填充字符串：Dd，用x在其左侧填充成10位的字符串，
     *     则为：xxxxxxxxDd
     * </p>
     * <p>
     *     如果待字符串is null，则返回null。
     *     如果填充长度is null,或者小于原长度，则返回原待填充字符串。
     *     例如：待填充字符串：Dd，用x在其左侧填充成10位的字符串，则仍然为：Dd
     * </p>
     * <p>
     *     如果你有指定填充字符串，或者填充方向，我们会进行默认。
     *     填充字符串默认为：0，方向为：左。
     *     例如：待填充字符串：Dd，填充成10位的字符串，则为：00000000Dd
     * </p>
     * @param source 待填充的字符串
     * @param length 填充后的长度
     * @param str 填充的字符（串）
     * @param isRight 是否在原字符串的右侧填充
     * @return 填充后的字符串
     */
    public static String autoFill(String source, Integer length, String str, Boolean isRight) {

        // 初始化校验
        if (source == null || length == null || (source + "").length() >= length)
            return source + "";

        // 指定填充字符
        if (isEmpty(str))
            str = "0";

        // 指定填充方向
        if (isRight == null)
            isRight = false;


        // 字符填充长度
        int count = (source + "").length();

        StringBuilder sb = new StringBuilder(length);

        // 右填充
        if (isRight) {
            sb.append(source);
        }

        // 字符填充
        int size = length - count;
        for (int i = 0; i < size; i++) {
            sb.append(str);
        }

        // 左填充
        if (!isRight) {
            sb.append(source);
        }

        return sb.toString();
    }


    /**
     * 字符串中只有数字。如果只含有数字，则返回true，反之，返回false.
     * @param str 待检测字符串
     * @return 是否只含有数字（0-9）
     */
    public static boolean hasOnlyNum(String str) {

        if (isEmpty(str))
            return false;

        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取随机字符串
     * @param length 生成字符串的长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder();
        char[] str = "0123456789abcdefghijkmlnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * str.length);
            result.append(str[index]);
        }
        return result.toString();
    }

    /**
     * 删除字符串开始的字符串
     * <p>
     *     如字符串：abcdefg，删除abc，得到的字符串为defg
     * </p>
     * @param str 原始字符串
     * @param remove 需要移除的字符串
     * @return 移除后的字符串
     */
    public static String removeStart(String str, String remove) {
        if (isNotEmpty(str) && isNotEmpty(remove)) {
            return str.startsWith(remove) ? str.substring(remove.length()) : str;
        } else {
            return str;
        }
    }

}
