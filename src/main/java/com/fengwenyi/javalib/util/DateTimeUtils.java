package com.fengwenyi.javalib.util;

import com.alibaba.fastjson.JSON;
import com.fengwenyi.javalib.bean.PastTimeDescBean;

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
 * <p>
 *     关于为什么没有推出预定义时间格式呢?
 *     因为每个人的需求不一样，不是很好定义，
 *     推荐名称为：DateTimePattern
 *     yyyyMMddHHmmss   → 2019-12-17 10:37:21
 *     yyyyMMdd         → 2019-12-17
 *     yyMMddHHmm       → 19-12-17 10:37
 *     HHmmss           → 10:37:21
 *                      → 19.12.17 10:37
 *                      → 12.17 10:37
 *                      → 2019/12/17 10:37:21
 *                      → 2019/12/17 10:37
 *                      → 2019/12/17
 * </p>
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
     * 获取 LocalDateTime的秒数
     * @param localDateTime 时间 {@link LocalDateTime}
     * @return 时间戳（秒数）
     */
    public static Long toSecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 自然语言描述时间过去多久
     * <p>
     *     基于Java 8 LocalDateTime
     * </p>
     * @param localDateTime 时间({@link LocalDateTime})
     * @return {@link PastTimeDescBean}
     */
    public static PastTimeDescBean descPastTime(LocalDateTime localDateTime) {

        PastTimeDescBean pastTimeDescBean = new PastTimeDescBean();

        if (localDateTime == null)
            return pastTimeDescBean;

        // 时间毫秒数
        long sourceTimeSecond = DateTimeUtils.toMillisecond(localDateTime);

        // 当前时间毫秒数
        long nowTimeSecond = Instant.now().toEpochMilli();

        // 时间秒数差
        long dateDiff = nowTimeSecond - sourceTimeSecond;

        if (dateDiff >= 0) {
            long dateTemp1 = dateDiff / 1000; // 秒
            long dateTemp2 = dateTemp1 / 60;   // 分钟
            long dateTemp3 = dateTemp2 / 60;   // 小时
            long dateTemp4 = dateTemp3 / 24;   // 天数
            long dateTemp5 = dateTemp4 / 30;   // 月数
            long dateTemp6 = dateTemp5 / 12;   // 年数
            if (dateTemp6 > 0) {
                pastTimeDescBean.setNumber(dateTemp6);
                pastTimeDescBean.setUnit("year");
            } else if (dateTemp5 > 0) {
                pastTimeDescBean.setNumber(dateTemp5);
                pastTimeDescBean.setUnit("month");
            } else if (dateTemp4 > 0) {
                pastTimeDescBean.setNumber(dateTemp4);
                pastTimeDescBean.setUnit("day");
            } else if (dateTemp3 > 0) {
                pastTimeDescBean.setNumber(dateTemp3);
                pastTimeDescBean.setUnit("hour");
            } else if (dateTemp2 > 0) {
                pastTimeDescBean.setNumber(dateTemp2);
                pastTimeDescBean.setUnit("minute");
            } else if (dateTemp1 > 0) {
                pastTimeDescBean.setNumber(dateTemp1);
                pastTimeDescBean.setUnit("second");
            } else {
                pastTimeDescBean.setNumber(dateDiff);
                pastTimeDescBean.setUnit("just");
            }
        }
        return pastTimeDescBean;
    }

    /**
     * 中文描述时间过去多久了
     * @param localDateTime {@link LocalDateTime}
     * @return 中文语言描述时间过去多久了
     */
    public static String descPastTimeChn(LocalDateTime localDateTime) {
        PastTimeDescBean pastTimeDescBean = descPastTime(localDateTime);
        Long number = pastTimeDescBean.getNumber();
        String unit = pastTimeDescBean.getUnit();
        System.out.println(JSON.toJSONString(pastTimeDescBean));
        String desc = "";
        switch (unit) {
            case "year":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + "年前";
                break;
            case "month":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + "个月前";
                break;
            case "day":
                if (number == 1) {
                    desc = "昨天";
                } else {
                    desc = NumberToChnUtils.NumberToChn(number.intValue()) + "天前";
                }
                break;
            case "hour":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + "小时前";
                break;
            case "minute":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + "分钟前";
                break;
            case "second":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + "秒前";
                break;
            case "just":
                desc = "刚刚";
                break;
        }
        return desc;

    }

    /**
     * 英文描述时间过去多久了
     * @param localDateTime {@link LocalDateTime}
     * @return 英文语言描述时间过去多久了
     */
    public static String descPastTimeEn(LocalDateTime localDateTime) {
        PastTimeDescBean pastTimeDescBean = descPastTime(localDateTime);
        Long number = pastTimeDescBean.getNumber();
        String unit = pastTimeDescBean.getUnit();
        System.out.println(JSON.toJSONString(pastTimeDescBean));
        String desc = "";
        switch (unit) {
            case "year":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + " years ago";
                break;
            case "month":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + " months ago";
                break;
            case "day":
                if (number == 1) {
                    desc = "yesterday";
                } else {
                    desc = NumberToChnUtils.NumberToChn(number.intValue()) + " days ago";
                }
                break;
            case "hour":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + " hours ago";
                break;
            case "minute":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + " minutes ago";
                break;
            case "second":
                desc = NumberToChnUtils.NumberToChn(number.intValue()) + " seconds ago";
                break;
            case "just":
                desc = "just";
                break;
        }
        return desc;

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
        return date.toInstant();
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
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
