package com.fengwenyi.javalib.generate;

/**
 * traceId 工具类
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-04-23
 */
public class TraceIdUtils {

    /**
     * 用本地线程保存traceId
     */
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置traceId
     */
    public static void set() {
        threadLocal.set(IdUtils.genId());
    }

    /**
     * 设置traceId
     * @param id traceId
     */
    public static void set(String id) {
        threadLocal.set(id);
    }

    /**
     * 获取traceId
     * @return 返回traceId
     */
    public static String get() {
        return threadLocal.get();
    }

    /**
     * 清楚traceId
     */
    public static void remove() {
        threadLocal.remove();
    }

}
