package com.fengwenyi.javalib.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * 日期和时间工具类
 *
 * <p>目前功能如下：</p>
 * <ul>
 *     <li>{@link Date} 格式化成字符串</li>
 *     <li>{@link LocalDateTime} 格式化成字符串</li>
 *     <li>{@link Instant} 格式化成字符串</li>
 *     <li>{@link Long} 时间戳（毫秒）格式化成字符串</li>
 *     <li>时间字符串解析为 {@link Date}</li>
 *     <li>时间字符串解析为 {@link Instant}</li>
 *     <li>时间字符串解析为 {@link LocalDateTime}</li>
 *     <li>将 {@link Date} 转换成时间戳（毫秒数）</li>
 *     <li>将 {@link LocalDateTime} 转换成时间戳（毫秒数）</li>
 *     <li>将 {@link LocalDateTime} 转换成时间戳（秒数）</li>
 *     <li>自然语言描述过去的时间（中/英文）</li>
 * </ul>
 *
 *
 * <br>
 *     关于时间，我们一般会用来做什么呢？
 *     <ul>
 *         <li>格式化</li>
 *         <li>类型转换</li>
 *         <li>计算</li>
 *         <li>比较大小</li>
 *         <li>时间范围</li>
 *     </ul>
 *
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019-12-11
 * @see Date
 * @see Instant
 * @see LocalDate
 * @see LocalTime
 * @see LocalDateTime
 * @see OffsetDateTime
 * @see ZoneOffset
 * @see ZoneId
 * @see ZonedDateTime
 */
public class DateTimeUtils {

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "HH:mm:ss";

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
     * @param date {@link LocalDate}
     * @param pattern 描述日期格式的模式
     * @return 满足指定格式的日期字符串
     */
    public static String format(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * 将时间格式化成字符串
     * @param date 时间 {@link Date}
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间格式化成字符串
     * <p>
     *     提示：不能转换秒
     * </p>
     * @param timestamp 时间戳（毫秒）
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(Long timestamp, String pattern) {
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
     * 将时间格式化成字符串
     * @param offsetDateTime 偏移日期时间（OffsetDateTime）
     * @param pattern 描述日期和时间格式的模式
     * @return 满足指定格式的时间字符串
     */
    public static String format(OffsetDateTime offsetDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return offsetDateTime.format(formatter);
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
     * 字符串格式的日期解析成日期格式
     * @param dateStr 字符串格式的日期时间
     * @param pattern 描述日期和时间格式的模式
     * @return 日期时间格式 {@link LocalDate}
     */
    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, formatter);
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
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取 LocalDate 的毫秒数
     * @param localDate 时间 {@link LocalDate}
     * @return 毫秒数
     */
    public static Long toMillisecond(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取 LocalDateTime的秒数
     * @param localDateTime 时间 {@link LocalDateTime}
     * @return 时间戳（秒数）
     */
    public static Long toSecond(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 将 Instant 转换成 LocalDateTime
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将 Date 转换成 Instant
     * @param date {@link Date}
     * @return {@link Instant}
     */
    public static Instant toInstant(Date date) {
        // return date.toInstant();
        return Instant.ofEpochMilli(date.getTime());
    }

    /**
     * 将 Date 转换成 LocalDateTime
     * @param date {@link Date}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(toInstant(date));
    }

    /**
     * 比较两个时间大小，简言之，{@code before < after} 是否成立
     * <p>
     *     假定有两个时间，before和after，如果before小于after，返回 {@code true }，
     *     反之，返回 {@code false}
     * </p>
     * @param before 小的是
     * @param after 大的时间
     * @return {@code before < after} 是否成立
     */
    public static Boolean isBefore(LocalDateTime before, LocalDateTime after) {
        return before.isBefore(after);
    }

    /**
     * 时间戳（毫秒）转为 LocalDateTime
     * @param timestamp 时间戳（毫秒）
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        if (Objects.isNull(timestamp)) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime 转为 Date
     * @param localDateTime {@link java.time.LocalDateTime}
     * @return {@link java.util.Date}
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Date.from(
                localDateTime
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    /**
     * LocalDate 转为 Date
     * @param localDate {@link java.time.LocalDate}
     * @return {@link java.util.Date}
     */
    public static Date toDate(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return Date.from(
                localDate
                        .atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    /**
     * LocalDateTime 转为 OffsetDateTime
     * @param localDateTime LocalDateTime
     * @return OffsetDateTime
     */
    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, ZoneId.systemDefault().getRules().getOffset(localDateTime));
    }

    /**
     * LocalDateTime 转为 OffsetDateTime
     * @param localDateTime LocalDateTime
     * @param zoneOffset ZoneOffset
     * @return OffsetDateTime
     */
    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return OffsetDateTime.of(localDateTime, zoneOffset);
    }

    /**
     * LocalDateTime 转为 OffsetDateTime
     *
     * <ul>
     * <li>{@code Z} - for UTC
     * <li>{@code +h}
     * <li>{@code +hh}
     * <li>{@code +hh:mm}
     * <li>{@code -hh:mm}
     * <li>{@code +hhmm}
     * <li>{@code -hhmm}
     * <li>{@code +hh:mm:ss}
     * <li>{@code -hh:mm:ss}
     * <li>{@code +hhmmss}
     * <li>{@code -hhmmss}
     * </ul>
     *
     * @param localDateTime LocalDateTime
     * @param offsetId ZoneOffset
     * @return OffsetDateTime
     * @see ZoneOffset#of(String)
     */
    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime, String offsetId) {
        return OffsetDateTime.of(localDateTime, ZoneOffset.of(offsetId));
    }

    /**
     * 校验时间合法性
     * @param source 待检查的时间字符串
     * @param pattern 时间字符串格式
     * @return 验证结果，true: 有效 / false: 无效
     */
    public static boolean isValid(String source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(source);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 获取年
     * @param date 日期 LocalDate
     * @return 年
     */
    public static int getYear(LocalDate date) {
        return date.getYear();
    }

    /**
     * 获取年-现在
     * @return 年
     */
    public static int getYear() {
        return getYear(LocalDate.now());
    }

    /**
     * 获取自然周的开始时间
     * @param localDate 日期
     * @return 一周的开始时间
     */
    public static LocalDateTime getStartOfNaturalWeek(LocalDate localDate){

        LocalDateTime nowDateTime = localDate.atStartOfDay();

        int dayOfWeek = localDate.getDayOfWeek().getValue();

        return nowDateTime.minusDays(dayOfWeek - 1).with(LocalTime.MIN);
    }

    /**
     * 获取当前月的开始时间
     * @param localDate 当前日期
     * @return 当前月的开始时间
     */
    public static LocalDateTime getStartOfMonth(LocalDate localDate) {
        LocalDateTime nowDateTime = localDate.atStartOfDay();
        return nowDateTime
                .with(TemporalAdjusters.firstDayOfMonth())
                .with(LocalTime.MIN);
    }

    /**
     * 判断是否在指定时间区间内
     * @param time 检测时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return true-在区间内；false-不在区间内
     * @since 2.2.1
     */
    public static boolean judgeInTimeDuration(LocalTime time, LocalTime startTime, LocalTime endTime) {
        if (endTime.isAfter(startTime)) {
            return time.isAfter(startTime) && time.isBefore(endTime);
        }
        return time.isAfter(startTime) || time.isBefore(endTime);
    }

    /**
     * 判断是否在指定时间区间内，含边界
     * @param time 检测时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return true-在区间内；false-不在区间内
     * @since 2.2.1
     */
    public static boolean judgeInTimeDurationWithBoundary(LocalTime time, LocalTime startTime, LocalTime endTime) {
        if (endTime.isAfter(startTime)) {
            return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
        }
        return time.compareTo(startTime) >= 0 || time.compareTo(endTime) <= 0;
    }


    /**
     * 时间戳转 LocalDateTime （当天最小值）
     * @param timestamp 时间戳
     * @return 当天最小值
     */
    public static LocalDateTime toLocalDateTimeMin(Long timestamp) {
        LocalDateTime localDateTime = toLocalDateTime(timestamp);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(LocalTime.MIN);
    }

    /**
     * 时间戳转 LocalDateTime （当天最大值）
     * @param timestamp 时间戳
     * @return 当天最大值
     */
    public static LocalDateTime toLocalDateTimeMax(Long timestamp) {
        LocalDateTime localDateTime = toLocalDateTime(timestamp);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.with(LocalTime.MAX);
    }

}
