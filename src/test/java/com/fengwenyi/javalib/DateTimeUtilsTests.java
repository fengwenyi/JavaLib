package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.DateTimeUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

import java.util.Date;

/**
 * 测试日期和时间工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/11 17:52
 * @see {@link com.fengwenyi.javalib.util.DateTimeUtils}
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

}
