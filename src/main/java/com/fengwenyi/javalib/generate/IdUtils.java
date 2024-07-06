package com.fengwenyi.javalib.generate;

import java.util.UUID;

/**
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class IdUtils {

    /**
     * 包含"-"
     *
     * @return UUID
     */
    public static synchronized String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 基于UUID生成ID，不含"-"
     *
     * @return ID字符串
     */
    public static String generateId() {
        String uuIdStr = generateUUID();
        return uuIdStr.replaceAll("-", "");
    }

}
