package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.constant.LogLevel;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 打印工具类
 * @author Erwin Feng
 * @since 2019-05-22 22:34
 */
public class PrintUtils {

    /**
     * 打印开关，默认开启<br>
     * 无效， 已弃用
     * @deprecated {@link LogLevel}
     */
    @Deprecated
     public static boolean DEBUG = true;

    public static LogLevel LEVEL = LogLevel.INFO;

    /**
     * 控制台打印INFO日志
     * 如果不想输出，可通过 [DEBUG] 关闭
     * @param x 待打印
     */
    public static void info(Object x) {
        if (LEVEL.ordinal() >= LogLevel.INFO.ordinal()) {
            String className = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String str = String.format("%s %s \033[32;4mINFO\033[0m \033[36;4m%s\033[0m : %s", LocalDate.now(), LocalTime.now(), className + "#" + methodName, x);
            System.out.println(str);
        }
    }

    /**
     * 控制台打印INFO日志
     * 如果不想输出，可通过 [DEBUG] 关闭
     * @param x 待打印
     */
    public static void warn(Object x) {
        if (LEVEL.ordinal() > LogLevel.WARN.ordinal()) {
            String className = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String str = String.format("%s %s \033[33;4mWARN\033[0m \033[36;4m%s\033[0m : %s", LocalDate.now(), LocalTime.now(), className + "#" + methodName, x);
            System.out.println(str);
        }
    }

    /**
     * 控制台打印INFO日志
     * 如果不想输出，可通过 [DEBUG] 关闭
     * @param x 待打印
     */
    public static void error(Object x) {
        if (LEVEL.ordinal() > LogLevel.ERROR.ordinal()) {
            String className = new Exception().getStackTrace()[1].getClassName();
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String str = String.format("%s %s \033[31;4mERROR\033[0m \033[36;4m%s\033[0m : %s", LocalDate.now(), LocalTime.now(), className + "#" + methodName, x);
            System.out.println(str);
        }
    }

}
