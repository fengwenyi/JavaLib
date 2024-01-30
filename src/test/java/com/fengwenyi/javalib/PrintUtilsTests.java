package com.fengwenyi.javalib;

import com.fengwenyi.javalib.constant.LogLevel;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-09
 */
public class PrintUtilsTests {

    @Test
    public void testLevel() {

        PrintUtils.LEVEL = LogLevel.DEBUG;
        PrintUtils.info("test debug");

        PrintUtils.LEVEL = LogLevel.INFO;
        PrintUtils.info("test info");

        PrintUtils.LEVEL = LogLevel.ERROR;
        PrintUtils.info("test error");
    }

}
