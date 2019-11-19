package com.fengwenyi.javalib.time;

import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Erwin Feng
 * @since 2019-05-28 23:07
 */
public class InstantTest {

    // 时间戳相差计算
    @Test
    public void instantDiffer() {
        Instant inst1 = Instant.now();
        System.out.println("Inst1 : " + inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(3600 * 24));
        System.out.println("Inst2 : " + inst2);

        PrintUtils.info("Difference in milliseconds : " + Duration.between(inst1, inst2).toMillis());
        PrintUtils.info("Difference in Days : " + Duration.between(inst1, inst2).toDays());
        PrintUtils.info("Difference in Hours : " + Duration.between(inst1, inst2).toHours());
        PrintUtils.info("Difference in Minutes : " + Duration.between(inst1, inst2).toMinutes());
        PrintUtils.info("Difference in Nanos : " + Duration.between(inst1, inst2).toNanos());

        PrintUtils.info("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());
        PrintUtils.info("Difference in Nanos : " + Duration.between(inst1, inst2).getNano());
        PrintUtils.info("Difference in Units : " + Duration.between(inst1, inst2).getUnits());
    }

    private Long differMillis(Instant start, Instant end) {
        return Duration.between(start, end).toMillis();
    }

    private Long differSeconds(Instant start, Instant end) {
        return Duration.between(start, end).getSeconds();
    }

    private Long differMinutes(Instant start, Instant end) {
        return Duration.between(start, end).toMinutes();
    }

    private Long differHours(Instant start, Instant end) {
        return Duration.between(start, end).toHours();
    }

    private Long differDays(Instant start, Instant end) {
        return Duration.between(start, end).toDays();
    }

    @Test
    public void testInstantDiffer2() {
        Instant start = Instant.now();
        Instant end = start.plus(Duration.ofSeconds(3600 * 24));

        PrintUtils.info("start : " + start);
        PrintUtils.info("end   : " + end);
        PrintUtils.info();
        PrintUtils.info("differ " + differDays(start, end) + " days");
        PrintUtils.info("differ " + differHours(start, end) + " hours");
        PrintUtils.info("differ " + differMinutes(start, end) + " minutes");
        PrintUtils.info("differ " + differSeconds(start, end) + " seconds");
        PrintUtils.info("differ " + differMillis(start, end) + " millis");

        /*
        [2019-05-28 23:35:32.652] start : 2019-05-28T15:35:32.579Z
        [2019-05-28 23:35:32.652] end   : 2019-05-29T15:35:32.579Z

        [2019-05-28 23:35:32.652] differ 1 days
        [2019-05-28 23:35:32.653] differ 24 hours
        [2019-05-28 23:35:32.653] differ 1440 minutes
        [2019-05-28 23:35:32.653] differ 86400 seconds
        [2019-05-28 23:35:32.653] differ 86400000 millis
         */
    }


}
