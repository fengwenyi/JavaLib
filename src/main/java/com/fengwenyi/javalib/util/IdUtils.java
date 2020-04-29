package com.fengwenyi.javalib.util;

import java.util.UUID;

/**
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class IdUtils {

    /**
     * 不含"-"
     * @return a Id by UUID
     */
    public static String getIdByUUID() {
        String uuIdStr = UUID.randomUUID().toString();

        return uuIdStr.replaceAll("-", "");
    }

    /**
     * 包含"-"
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
