package com.fengwenyi.javalib.convert;

import com.fengwenyi.javalib.constant.DateTimePattern;

import java.time.format.DateTimeFormatter;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-12-02
 */
public class FormatterUtils {

    public static DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME);
    }

    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern(DateTimePattern.DATE);
    }

    public static DateTimeFormatter timeFormatter() {
        return DateTimeFormatter.ofPattern(DateTimePattern.TIME);
    }

}
