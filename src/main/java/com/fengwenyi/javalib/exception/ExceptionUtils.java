package com.fengwenyi.javalib.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 *
 * @author Erwin Feng
 * @since 2020/8/13
 */
public class ExceptionUtils {

    /**
     * 获取异常栈信息
     *
     * @param throwable 异常
     * @return 异常信息
     */
    public static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, Boolean.TRUE);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
