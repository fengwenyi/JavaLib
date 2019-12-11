package com.fengwenyi.javalib.time;

import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

import java.time.LocalTime;

/**
 * @author Erwin Feng
 * @since 2019-05-22 21:18
 */
public class Java8Time {

//    java.time包
    /*
    Instant：时间戳
    Duration：持续时间，时间差
    LocalDate：只包含日期，比如：2016-10-20
    LocalTime：只包含时间，比如：23:12:10
    LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
    Period：时间段
    ZoneOffset：时区偏移量，比如：+8:00
    ZonedDateTime：带时区的时间
    Clock：时钟，比如获取目前美国纽约的时间
    */

// java.time.format

    /*
    DateTimeFormatter：时间格式化
     */


    //start

    @Test
    public void simple() {

        PrintUtils.DEBUG = true;

        // 获取现在的时间
        LocalTime nowTime = LocalTime.now();
        PrintUtils.info(nowTime); // 22:30:26.816

        // 咦，怎么会有毫秒，不显示
        LocalTime nowTime2 = LocalTime.now().withNano(0);
        PrintUtils.info(nowTime2); // 22:48:30
    }


}
