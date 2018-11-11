package com.fengwenyi.javalib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
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
 * @see java.util.Date 时间
 *
 * java 8 time start
 * @see java.time.Instant
 * @see java.time.LocalDate
 * @see java.time.LocalTime
 * @see java.time.LocalDateTime
 * @see java.time.ZonedDateTime
 * java 8 time end
 */
public class DateTimeUtil {

    /** 设定周日为一周的开始 */
    private static final int FIRST_DAY_OF_WEEK = Calendar.SUNDAY;

    /**
     * 时间字符串转时间类型({@link java.util.Date})
     * @param source 时间字符串（需要满足指定格式）
     * @param format 指定格式
     * @return 时间类型({@link java.util.Date})
     * @throws ParseException 格式化出错
     */
    public static Date stringToDate(String source, String format) throws ParseException {

        if (StringUtil.isEmpty(source))
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(source);
    }

    /**
     * 时间({@link java.util.Date})转时间字符串
     * @param date 时间类型({@link java.util.Date})
     * @param format 指定格式
     * @return 满足指定格式的时间字符串
     */
    public static String dateToString(Date date, String format) {

        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 给定时间，获取年份。
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
     * @return 时间({@link java.util.Date})，如果date is null，将返回null
     */
    public static Date getFirstDateOfSeason(Date date) {

        if (date == null)
            return null;

        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    /**
     * 获取季度的最后一天
     * @param date 时间({@link java.util.Date})
     * @return 时间({@link java.util.Date})，如果date is null，将返回null
     */
    public static Date getLastDateOfSeason(Date date) {

        if (date == null)
            return null;

        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    /**
     * 获取季度的天数
     * @param date 时间({@link java.util.Date})
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
     * @param date 时间({@link java.util.Date})
     * @return 天数，如果date is null，将返回null
     */
    public static Integer getRemainingDayOfSeason(Date date) {

        if (date == null)
            return null;

        return getDayNumOfSeason(date) - getDayOfSeason(date);
    }

    /**
     * 获取季度已过的天数
     * @param date 时间({@link java.util.Date})
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
     * 时间测试类
     */

    /**
     * 自然语言描述时间过去多久
     */

}
