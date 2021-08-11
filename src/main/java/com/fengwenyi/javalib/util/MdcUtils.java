package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.generate.IdUtils;
import com.fengwenyi.javalib.constant.Variable;
import org.slf4j.MDC;

/**
 * MDC Utils
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-08-10
 */
public class MdcUtils {

    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /**
     * 请求入口记录日志
     * @param method 方法
     * @param keyword 关键字
     */
    public static void call(String method, String keyword) {
        MDC.put("method", method);
        MDC.put("keyword", keyword);
        MDC.put("traceId", traceId());
    }

    /**
     * 清除MDC
     */
    public static void clear() {
        MDC.clear();
        inheritableThreadLocal.remove();
    }

    /**
     * 获取traceId
     * @return traceId
     */
    public static String getTraceId() {
        String traceId = MDC.get(Variable.TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = inheritableThreadLocal.get();
        }
        return traceId;
    }

    /**
     * 设置traceId
     * @param traceId traceId
     */
    public static void setTraceId(String traceId) {
        MDC.put(Variable.TRACE_ID, traceId);
        inheritableThreadLocal.set(traceId);
    }

    /**
     * traceId处理，如果MDC有traceId，则返回，如果没有，则生成
     * @return traceId
     */
    public static String traceId() {
        String traceId = getTraceId();
        if (StringUtils.isEmpty(traceId)) {
            traceId = IdUtils.genId();
            setTraceId(traceId);
        }
        return traceId;
    }

}
