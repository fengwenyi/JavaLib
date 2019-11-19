package com.fengwenyi.javalib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * <ul>
 *     <li>时间格式化(时间格式与字符串之间的转换)</li>
 *     <li>自然语言描述</li>
 * </ul>
 * @author Wenyi Feng
 * @since 2018-09-25
 * @see com.fengwenyi.javalib.constant.DateTimeFormat 预定义时间格式
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

    /** 设定周日为一周的开始 */
    private static final int FIRST_DAY_OF_WEEK = Calendar.SUNDAY;

    /**
     * 将时间字符串解析成时间类型({@link Date})
     * @param source 时间字符串（需要满足指定格式）
     * @param format 指定格式
     * @return 时间类型({@link java.util.Date})
     * @throws ParseException 解析出错
     */
    public static Date parse(String source, String format) throws ParseException {

        if (StringUtils.isEmpty(source))
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(source);
    }

    /**
     * 获取毫秒数
     * @param date 时间类型({@link Date})
     * @return 毫秒数({@link java.lang.Long})
     */
    public static Long toLong(Date date) {
        if (date == null)
            return null;
        return date.getTime();
    }

    /**
     * 时间({@link Date})格式化成时间字符串
     * @param source 时间类型({@link Date}) or 秒数/毫秒数({@link Long})
     * @param format 指定格式
     * @return 满足指定格式的时间字符串
     */
    public static String format(Object source, String format) {

        if (source == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(source);
    }

    /**
     * 给定时间，获取年份。
     * @param date 时间({@link Date})
     * @return 年份（Integer）,如果date is null，将返回null
     */
    public static Integer getYear(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 给定时间，获取月份。
     * @param date 时间({@link Date})
     * @return 月份（Integer）,如果date is null，将返回null
     */
    public static Integer getMonth(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 给定时间，获取日期。
     * @param date 时间({@link Date})
     * @return 日期（Integer）,如果date is null，将返回null
     */
    public static Integer getDay(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DATE);
    }

    /**
     * 给定时间，获取一周中的第几天。
     *
     * <p>
     *     从周日开始计算，比如，周日则返回1，周一返回2，...，周六返回7
     * </p>
     *
     * @param date 时间({@link Date})
     * @return （Integer）,如果date is null，将返回null
     */
    public static Integer getDayOfWeek(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 给定时间，获取一月中的第几天。
     * @param date 时间({@link Date})
     * @return （Integer）,如果date is null，将返回null
     */
    public static Integer getDayOfMonth(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 给定时间，获取一年中的第几天。
     * @param date 时间({@link Date})
     * @return （Integer）,如果date is null，将返回null
     */
    public static Integer getDayOfYear(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 给定时间，获取一年中的第几周。
     * @param date 时间({@link Date})
     * @return （Integer）,如果date is null，将返回null
     */
    public static Integer getWeekOfYear(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取日期所在周的开始时间（约定为周日）
     * @param date 时间({@link Date})
     * @return 时间({@link java.util.Date})
     */
    public static Date getStartOfWeek(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return calendar.getTime();
    }

    /**
     * 获取日期所在周的结束时间（约定为周六）
     * @param date 时间({@link Date})
     * @return 时间({@link java.util.Date})
     */
    public static Date getEndOfWeek(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        return calendar.getTime();
    }

    /**
     * 获取所在月剩余天数
     * @param date 时间({@link Date})
     * @return 月剩余天数，如果date is null，将返回null
     */
    public static Integer getRemainingDayOfMonth(Date date) {

        if (date == null)
            return null;

        // 月天数
        int days = getDayNumOfMonth(date);
        // 第几天
        int dayOfMonth = getDayOfMonth(date);

        return days - dayOfMonth;
    }

    /**
     * 获取月的天数
     * @param date 时间({@link Date})
     * @return 月天数，如果date is null，将返回null
     */
    public static Integer getDayNumOfMonth(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月的第一天
     * @param date 时间({@link Date})
     * @return 时间({@link java.util.Date})，如果date is null，将返回null
     */
    public static Date getFirstDateOfMonth(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 获取月的最后一天
     * @param date 时间({@link Date})
     * @return 时间({@link java.util.Date})，如果date is null，将返回null
     */
    public static Date getLastDateOfMonth(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取季度的第一天
     * @param date 时间({@link Date})
     * @return 时间({@link java.util.Date})，如果date is null，将返回null
     */
    public static Date getFirstDateOfSeason(Date date) {

        if (date == null)
            return null;

        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    /**
     * 获取季度的最后一天
     * @param date 时间({@link Date})
     * @return 时间({@link java.util.Date})，如果date is null，将返回null
     */
    public static Date getLastDateOfSeason(Date date) {

        if (date == null)
            return null;

        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    /**
     * 获取季度的天数
     * @param date 时间({@link Date})
     * @return 天数，如果date is null，将返回null
     */
    public static Integer getDayNumOfSeason(Date date) {

        if (date == null)
            return null;

        int day = 0;
        Date[] seasonDates = getSeasonDate(date);
        for (Date d : seasonDates) {
            day += getDayOfMonth(d);
        }
        return day;
    }

    /**
     * 获取季度剩余的天数
     * @param date 时间({@link Date})
     * @return 天数，如果date is null，将返回null
     */
    public static Integer getRemainingDayOfSeason(Date date) {

        if (date == null)
            return null;

        return getDayNumOfSeason(date) - getDayOfSeason(date);
    }

    /**
     * 获取季度已过的天数
     * @param date 时间({@link Date})
     * @return 天数，如果date is null，将返回null
     */
    public static Integer getDayOfSeason(Date date) {

        if (date == null)
            return null;

        int day = 0;
        Date[] seasonDates = getSeasonDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        if (month == Calendar.JANUARY || month == Calendar.APRIL || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
            day = getDayOfMonth(seasonDates[0]);
        } else if (month == Calendar.FEBRUARY || month == Calendar.MAY || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
            day = getDayOfMonth(seasonDates[0])	+ getDayOfMonth(seasonDates[1]);
        } else if (month == Calendar.MARCH || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
            day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1])	+ getDayOfMonth(seasonDates[2]);
        }
        return day;
    }

    /**
     * 根据输入的日期，以数组的方式返回当前日期所在年的季度下的年月
     * @param date 日期型
     * @return 返回数组，数组里包含当前季度的3个月份
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int nSeason = getQuarter(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 获取当前是几季度
     * <p>
     *     1：第一季度
     *     2：第二季度
     *     3：第三季度
     *     4：第四季度
     * </p>
     * @param date 日期
     * @return 返回当前季度数值，如果date is null,则返回null
     */
    public static Integer getQuarter(Date date) {

        if (date == null)
            return null;

        int season = 1;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
        }
        return season;
    }

    /**
     * java 8 时间
     */

    /**
     * 自然语言描述时间过去多久了
     * @param source 时间({@link Date})
     * @return 自然语言描述过去的时间
     */
    public static String descPastTime(Date source) {

        if (source == null)
            return null;

        long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数

        String msg = "";

        long dateDiff = nowTime - source.getTime();

        if (dateDiff >= 0) {
            long dateTemp1 = dateDiff  / 1000; // 秒
            long dateTemp2 = dateTemp1 / 60;   // 分钟
            long dateTemp3 = dateTemp2 / 60;   // 小时
            long dateTemp4 = dateTemp3 / 24;   // 天数
            long dateTemp5 = dateTemp4 / 30;   // 月数
            long dateTemp6 = dateTemp5 / 12;   // 年数
            if (dateTemp6 > 0)
                msg = dateTemp6 + "年前";
            else if (dateTemp5 > 0)
                msg = dateTemp5 + "个月前";
            else if (dateTemp4 > 0)
                msg = dateTemp4 + "天前";
            else if (dateTemp3 > 0)
                msg = dateTemp3 + "小时前";
            else if (dateTemp2 > 0)
                msg = dateTemp2 + "分钟前";
            else if (dateTemp1 > 0)
                msg = dateTemp1 + "秒前";
            else
                msg = "刚刚";
        }
        return msg;

    }

    /**
     * 计算两个时间点相差多少毫秒
     * @param start 开始时间点（Instant）
     * @param end   结束时间点（Instant）
     * @return 相差毫秒数(long)
     */
    public static long differMillis(Instant start, Instant end) {
        return Duration.between(start, end).toMillis();
    }

    /**
     * 计算两个时间点相差多少秒
     * 注意，返回只保留整数部分
     * @param start 开始时间点（Instant）
     * @param end   结束时间点（Instant）
     * @return 相差秒数(long)
     */
    public static long differSeconds(Instant start, Instant end) {
        return Duration.between(start, end).getSeconds();
    }

    /**
     * 计算两个时间点相差多少分钟
     * 注意，返回只保留整数部分
     * @param start 开始时间点（Instant）
     * @param end   结束时间点（Instant）
     * @return 相差分钟数(long)
     */
    public static long differMinutes(Instant start, Instant end) {
        return Duration.between(start, end).toMinutes();
    }

    /**
     * 计算两个时间点相差多少小时
     * 注意，返回只保留整数部分
     * @param start 开始时间点（Instant）
     * @param end   结束时间点（Instant）
     * @return 相差小时数(long)
     */
    public static long differHours(Instant start, Instant end) {
        return Duration.between(start, end).toHours();
    }

    /**
     * 计算两个时间点相差多少天
     * 注意，返回只保留整数部分
     * @param start 开始时间点（Instant）
     * @param end   结束时间点（Instant）
     * @return 相差天数(long)
     */
    public static long differDays(Instant start, Instant end) {
        return Duration.between(start, end).toDays();
    }

    /**
     * 时间点格式化
     * @param instant 时间点（Instant）
     * @param format 格式（String）
     * @return 字符串时间格式
     */
    public static String format(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     *将日期字符串(形如 {@code 2019-07-09} )转为 Instant，结果：{@code 2019-07-08T16:00:00Z}
     *
     * @param source 日期字符串，如 {@code 2019-07-09}
     * @return Instant {@code 2019-07-08T16:00:00Z}
     */
    public static Instant parseDate(String source) {
        LocalDate date = LocalDate.parse(source);
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    /**
     * 将 时间字符串 转为 Instant
     * @param source 时间字符串
     * @param format 格式
     * @return Instant
     */
    public static Instant parseDateTime(String source, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(source, dateTimeFormatter);
        return dateTime.atZone(ZoneOffset.systemDefault()).toInstant();
    }

}
