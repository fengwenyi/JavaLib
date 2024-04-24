package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.constant.StringConstant;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * <ul>
 *     <li>字符串判空</li>
 *     <li>字符串判非空</li>
 * </ul>
 *
 * @author Erwin Feng
 */
public class StrUtils {

    /** 空的字符串（""） */
    public static final String BLANK = "";

    /**
     * 判断字符串是否为空
     *
     * <ul>
     *     <li>null     - true</li>
     *     <li>""       - true</li>
     *     <li>" "      - true</li>
     *     <li>"null"   - true</li>
     *     <li>" null " - true</li>
     * </ul>
     *
     * @param str 待判断的字符串
     * @return 是否是空字符串，true：空字符串；false：非空字符串
     */
    public static boolean isBlank(String str) {
        if (Objects.isNull(str))
            return true;
        if ("".equals(str.trim()))
            return true;
        if ("null".equals(str.trim()))
            return true;
        return str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 待判断的字符串
     * @return 是否是空字符串，true：非空字符串；false：空字符串
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    /**
     * 自动填充。比如，待填充是：11，填充长度：10，则填充后的字符串为：0000000011
     *
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
     * 比如：待填充字符串：Dd，用x在其左侧填充成10位的字符串，
     * 则为：xxxxxxxxDd
     * </p>
     * <p>
     * 如果待字符串is null，则返回null。
     * 如果填充长度is null,或者小于原长度，则返回原待填充字符串。
     * 例如：待填充字符串：Dd，用x在其左侧填充成10位的字符串，则仍然为：Dd
     * </p>
     * <p>
     * 如果你有指定填充字符串，或者填充方向，我们会进行默认。
     * 填充字符串默认为：0，方向为：左。
     * 例如：待填充字符串：Dd，填充成10位的字符串，则为：00000000Dd
     * </p>
     *
     * @param source  待填充的字符串
     * @param length  填充后的长度
     * @param str     填充的字符（串）
     * @param isRight 是否在原字符串的右侧填充
     * @return 填充后的字符串
     */
    public static String autoFill(String source, Integer length, String str, Boolean isRight) {

        // 初始化校验
        if (source == null || length == null || (source + "").length() >= length)
            return source + "";

        // 指定填充字符
        if (isBlank(str))
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
     *
     * @param str 待检测字符串
     * @return 是否只含有数字（0-9）
     */
    public static boolean hasOnlyNum(String str) {

        if (isBlank(str))
            return false;

        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取随机字符串
     *
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
     * 如字符串：abcdefg，删除abc，得到的字符串为defg
     * </p>
     *
     * @param str    原始字符串
     * @param remove 需要移除的字符串
     * @return 移除后的字符串
     */
    public static String removeStart(String str, String remove) {
        if (isNotBlank(str) && isNotBlank(remove)) {
            return str.startsWith(remove) ? str.substring(remove.length()) : str;
        } else {
            return str;
        }
    }

    /**
     * 生成一个指定长度的星号（*）字符串
     *
     * @param length 生成星号的长度
     * @return 生成一个指定长度的星号（*）字符串
     */
    public static String generateStar(int length) {
        StringJoiner sj = new StringJoiner("", "", "");
        for (int i = 0; i < length; i++) {
            sj.add("*");
        }
        return sj.toString();
    }

    /**
     * 获取字符串左边指定长度的字符串。
     * <p>
     * 例如，字符串是：张三，截取长度为1，得到的结果是：张。
     * </p>
     * <p>
     * 注意，如果需要截取的长度大于字符串的长度，那么会返回整个字符串。
     * 如截取的长度是3，那么得到的结果是：张三。
     * </p>
     *
     * @param source 需要截取的字符串
     * @param length 需要获取左边字符串的长度
     * @return 返回字符串左边指定长度的字符串
     */
    public static String getLeft(String source, int length) {
        if (StrUtils.isBlank(source)
                || length < 1) {
            return StringConstant.BLANK;
        }
        if (length > source.length()) {
            PrintUtils.warn("length(" + length + ") > source's length(" + source.length() + ")");
            length = source.length();
        }
        return source.substring(0, length);
    }

    /**
     * 获取字符串右边指定长度的字符串。
     * <p>
     * 例如，字符串是：张三，截取长度为1，得到的结果是：三。
     * </p>
     * <p>
     * 注意，如果需要截取的长度大于字符串的长度，那么会返回整个字符串。
     * 如截取的长度是3，那么得到的结果是：张三。
     * </p>
     *
     * @param source 需要截取的字符串
     * @param length 需要获取右边字符串的长度
     * @return 返回字符串右边指定长度的字符串
     */
    public static String getRight(String source, int length) {
        if (StrUtils.isBlank(source)
                || length < 1) {
            return StringConstant.BLANK;
        }
        if (length > source.length()) {
            PrintUtils.warn("length(" + length + ") > source's length(" + source.length() + ")");
            length = source.length();
        }
        return source.substring(source.length() - length);
    }

    // 截取前缀后的字符串
    public static String substringAfter(String content, String prefix) {
        if (isBlank(content)) {
            return "";
        }
        if (isBlank(prefix)) {
            return content;
        }
        return content.substring(prefix.length());
    }

    // 将字符串的第一个字符大写
    public static String lowerCaseFirst(String content) {
        if (isBlank(content)) {
            return "";
        }
        String first = content.substring(0, 1);
        String after = content.substring(1);
        return first.toLowerCase() + after;
    }

}
