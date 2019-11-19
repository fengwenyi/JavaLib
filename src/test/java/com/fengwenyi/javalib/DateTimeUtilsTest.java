package com.fengwenyi.javalib;

import net.iutil.javalib.constant.DateTimeFormat;
import net.iutil.javalib.util.DateTimeUtils;
import net.iutil.javalib.util.PrintUtils;
import org.junit.Test;

import java.time.Instant;

/**
 * @author Erwin Feng
 * @since 2019-05-29 18:18
 */
public class DateTimeUtilsTest {

    @Test
    public void instantFormat() {
        String format = DateTimeUtils.format(Instant.now(), DateTimeFormat.yyyy_MM_dd_HH_mm_ss);
        PrintUtils.info(format);
    }

    @Test
    public void date2Instant() {
        String date = "2019-07-09";
        Instant instant = DateTimeUtils.parseDate(date);
        PrintUtils.info(instant);
    }

    @Test
    public void dateTime2Instant() {
        String dateTime = "2019-07-09 18:00:00";
        Instant instant = DateTimeUtils.parseDateTime(dateTime, "yyyy-MM-dd HH:mm:ss");
        PrintUtils.info(instant);
    }

}
