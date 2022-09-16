package com.fengwenyi.javalib;

import com.fengwenyi.javalib.convert.DateTimeUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

}
