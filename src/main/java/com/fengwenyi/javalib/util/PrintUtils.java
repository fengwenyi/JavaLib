package com.fengwenyi.javalib.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 打印工具类
 * @author Erwin Feng
 * @since 2019-05-22 22:34
 */
public class PrintUtils {

    /** 打印开关，默认开启 */
    public static boolean DEBUG = true;

    /**
     * 控制台打印INFO日志
     * 如果不想输出，可通过 [DEBUG] 关闭
     * @param x 待打印
     */
    public static void info(Object x) {
        if (DEBUG) {
            String str = String.format("[%s %s] %s", LocalDate.now(), LocalTime.now(), x);
            System.out.println(str);
        }
    }
    public static void info() {
        if (DEBUG) {
            System.out.println();
        }
    }

}
