package com.fengwenyi.javalib.time;

import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Erwin Feng
 * @since 2019-05-22 22:51
 */
public class Format {

    @Test
    public void test() {
        PrintUtils.DEBUG = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY MM dd");
        PrintUtils.info(formatter.format(LocalDate.now()));
    }

}
