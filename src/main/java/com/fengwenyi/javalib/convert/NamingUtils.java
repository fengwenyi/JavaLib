package com.fengwenyi.javalib.convert;

import com.fengwenyi.javalib.constant.StringConstant;
import com.fengwenyi.javalib.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 命名工具类
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-27
 */
public class NamingUtils {

    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern UNDERLINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 驼峰转中划线
     * @param name 待转换的名称
     * @return 转换后的名称
     */
    public static String humpToMidline(String name) {
        if (StringUtils.isEmpty(name)) {
            return StringConstant.BLANK;
        }
        Matcher matcher = HUMP_PATTERN.matcher(name);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "-" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * 驼峰转中划线
     * @param name 待转换的名称
     * @return 转换后的名称
     */
    public static String humpToUnderline(String name) {
        if (StringUtils.isEmpty(name)) {
            return StringConstant.BLANK;
        }
        Matcher matcher = HUMP_PATTERN.matcher(name);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * 下划线转驼峰
     * @param name 待转换的名称
     * @return 转换后的名称
     */
    public static String underlineToHump(String name) {
        if (StringUtils.isEmpty(name)) {
            return StringConstant.BLANK;
        }
        name = name.toLowerCase();
        Matcher matcher = UNDERLINE_PATTERN.matcher(name);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

}
