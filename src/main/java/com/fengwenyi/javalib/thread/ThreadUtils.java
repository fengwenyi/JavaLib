package com.fengwenyi.javalib.thread;

/**
 * 线程工具类
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-23
 */
public class ThreadUtils {

    /**
     * 获取当前类的名称
     * @return 全类名
     */
    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }

    /**
     * 获取当前方法的名称
     * @return 方法名
     */
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}
