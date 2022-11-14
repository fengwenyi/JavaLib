package com.fengwenyi.javalib;

import com.fengwenyi.javalib.convert.DateTimeUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.util.Date;

/**
 * 测试日期和时间工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/11 17:52
 * @see DateTimeUtils
 */
public class DateTimeUtilsTests {

    // java.util.Date 格式化成字符串时间
    @Test
    public void testDateFormat() {
        Date date = new Date();
        String format = DateTimeUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        PrintUtils.info(format);
        Date d = null;
        String s = DateTimeUtils.format(d, "yyyy-MM-dd HH:mm:ss");
        PrintUtils.info(s);
    }

    // 时间戳（毫秒，Long）格式化成字符串时间
    @Test
    public void testTimestampFormat() {
        Date date = new Date();
        Long millisecond = date.getTime();
        String format = DateTimeUtils.format(millisecond, "yyyy-MM-dd HH:mm:ss");
        PrintUtils.info(format);
    }

    // 比较两个是大小
    @Test
    public void testBefore() {
        LocalDateTime before = LocalDateTime.of(2019, 12, 1, 12, 12);
        LocalDateTime after = LocalDateTime.of(2019, 11, 1, 12, 12);
        Boolean isBefore = DateTimeUtils.isBefore(before, after);
        System.out.println(isBefore); // false

        before = LocalDateTime.of(2019, 11, 1, 12, 12);
        after = LocalDateTime.of(2019, 12, 1, 12, 12);
        isBefore = DateTimeUtils.isBefore(before, after);
        System.out.println(isBefore); // true
    }

    @Test
    public void testLongToLocalDateTime() {
    }

    @Test
    public void testGetStartOfNaturalWeek() {
        System.out.println(DateTimeUtils.getStartOfNaturalWeek(LocalDate.now()));
        System.out.println(DateTimeUtils.getStartOfNaturalWeek(LocalDate.of(2022, 9, 12)));
    }

    @Test
    public void testGetStartOfMonth() {
        System.out.println(DateTimeUtils.getStartOfMonth(LocalDate.now()));
        System.out.println(DateTimeUtils.getStartOfMonth(LocalDate.of(2022, 2, 28)));
    }

    @Test
    public void testInTimeDuration() {
        // 1. 一天内
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("17:00");
        LocalTime testTime = LocalTime.parse("12:00");
        boolean result = DateTimeUtils.judgeInTimeDuration(testTime, startTime, endTime);
        Assert.assertTrue(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        startTime = LocalTime.parse("10:00");
        endTime = LocalTime.parse("17:00");
        testTime = LocalTime.parse("23:00");
        result = DateTimeUtils.judgeInTimeDuration(testTime, startTime, endTime);
        Assert.assertFalse(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        // 2. 第二天
        startTime = LocalTime.parse("22:00");
        endTime = LocalTime.parse("02:00");
        testTime = LocalTime.parse("23:00");
        result = DateTimeUtils.judgeInTimeDuration(testTime, startTime, endTime);
        Assert.assertTrue(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        startTime = LocalTime.parse("22:00");
        endTime = LocalTime.parse("02:00");
        testTime = LocalTime.parse("10:00");
        result = DateTimeUtils.judgeInTimeDuration(testTime, startTime, endTime);
        Assert.assertFalse(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        startTime = LocalTime.parse("22:00");
        endTime = LocalTime.parse("02:00");
        testTime = LocalTime.parse("03:00");
        result = DateTimeUtils.judgeInTimeDuration(testTime, startTime, endTime);
        Assert.assertFalse(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        // 3. 时间边界
        // 此方法不含边界
        startTime = LocalTime.parse("22:00");
        endTime = LocalTime.parse("02:00");
        testTime = LocalTime.parse("22:00");
        result = DateTimeUtils.judgeInTimeDuration(testTime, startTime, endTime);
        Assert.assertFalse(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        startTime = LocalTime.parse("22:00");
        endTime = LocalTime.parse("02:00");
        testTime = LocalTime.parse("22:00");
        result = DateTimeUtils.judgeInTimeDurationWithBoundary(testTime, startTime, endTime);
        Assert.assertTrue(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));

        // 开始时间 与 结束时间 相等
        startTime = LocalTime.parse("22:00");
        endTime = LocalTime.parse("22:00");
        testTime = LocalTime.parse("22:00");
        result = DateTimeUtils.judgeInTimeDurationWithBoundary(testTime, startTime, endTime);
        Assert.assertTrue(result);
        System.out.println(String.format("开始时间：%s，结束时间：%s，测试时间：%s，测试结果：%b", startTime, endTime, testTime, result));
    }

    @Test
    public void testToDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = DateTimeUtils.toDate(localDateTime);
        System.out.println("LocalDateTime 转 Date: " + date);
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate 转 Date：" + DateTimeUtils.toDate(localDate));
        System.out.println(new Date());
    }

}
