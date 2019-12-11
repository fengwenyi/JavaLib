package com.fengwenyi.javalib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期和时间工具类
 *
 * <p>目前功能如下：</p>
 * <ul>
 *     <li>{@link Date} 格式化成字符串</li>
 *     <li>{@link LocalDateTime} 格式化成字符串</li>
 *     <li>{@link Instant} 格式化成字符串</li>
 *     <li>{@link Long} 时间戳（毫秒、秒）格式化成字符串</li>
 *     <li>时间字符串解析为 {@link Date}</li>
 *     <li>时间字符串解析为 {@link Instant}</li>
 *     <li>时间字符串解析为 {@link LocalDateTime}</li>
 *     <li>将 {@link Date} 转换成时间戳（毫秒数）</li>
 *     <li>将 {@link LocalDateTime} 转换成时间戳（毫秒数）</li>
 *     <li>将 {@link LocalDateTime} 转换成时间戳（秒数）</li>
 * </ul>
 *
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019-12-11
 * @see Date 时间
 *
 * java 8 time start
 * @see Instant
 * @see LocalDate
 * @see LocalTime
 * @see LocalDateTime
 * @see ZonedDateTime
 * java 8 time end
 */
public class DateTimeUtils {

    /**
     * 将时间格式化成字符串
     * @param dateTime {@link LocalDateTime}
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 将时间格式化成字符串
     * @param date 时间 {@link Date}
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(Date date, String pattern) {

        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间格式化成字符串
     * @param timestamp 时间戳（毫秒、秒）
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(Long timestamp, String pattern) {
        if (timestamp == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(timestamp);
    }

    /**
     * 将时间格式化成字符串
     * @param instant 时间点（Instant）
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(Instant instant, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串格式的日期时间解析成日期时间格式
     * @param dateTimeStr 字符串格式的日期时间
     * @param pattern 描述日期和时间格式的模式
     * @return 日期时间格式 {@link LocalDateTime}
     */
    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     *将日期字符串(形如 {@code 2019-07-09} )转为 Instant，结果：{@code 2019-07-08T16:00:00Z}
     *
     * @param source 日期字符串，如 {@code 2019-07-09}
     * @return Instant {@code 2019-07-08T16:00:00Z}
     */
    public static Instant parseInstant(String source) {
        LocalDate date = LocalDate.parse(source);
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    /**
     * 将 时间字符串 转为 Instant
     * @param source 时间字符串
     * @param pattern 格式
     * @return Instant
     */
    public static Instant parseInstant(String source, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(source, dateTimeFormatter);
        return dateTime.atZone(ZoneOffset.systemDefault()).toInstant();
    }

    /**
     * 将时间字符串解析成时间类型({@link Date})
     * @param source 时间字符串（需要满足指定格式）
     * @param pattern 指定格式
     * @return 时间类型({@link java.util.Date})
     */
    public static Date parseDate(String source, String pattern) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取毫秒数
     * @param date 时间类型({@link Date})
     * @return 毫秒数({@link java.lang.Long})
     */
    public static Long toMillisecond(Date date) {
        if (date == null)
            return null;
        return date.getTime();
    }

    /**
     * 获取 LocalDateTime的毫秒数
     * @param localDateTime 时间 {@link LocalDateTime}
     * @return 毫秒数
     */
    public static Long toMillisecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取 LocalDateTime的毫秒数
     * @param localDateTime 时间 {@link LocalDateTime}
     * @return 毫秒数
     */
    public static Long toSecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

}
