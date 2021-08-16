package com.fengwenyi.javalib.generate;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.fengwenyi.javalib.util.StringUtils;

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
    private static final TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    /**
     * 设置traceId
     */
    public static void set() {
        String traceId = IdUtils.genId();
        threadLocal.set(traceId);
        context.set(traceId);
    }

    /**
     * 设置traceId
     * @param traceId traceId
     */
    public static void set(String traceId) {
        threadLocal.set(traceId);
        context.set(traceId);
    }

    /**
     * 获取traceId
     * @return 返回traceId
     */
    public static String get() {
        String traceId = threadLocal.get();
        if (StringUtils.isEmpty(traceId)) {
            traceId = context.get();
        }
        return traceId;
    }

    /**
     * 清除traceId
     */
    public static void remove() {
        threadLocal.remove();
        context.remove();
    }

}
