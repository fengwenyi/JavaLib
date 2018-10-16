package com.fengwenyi.javalib.util;

/**
 * 进制转换工具类
 * @author Wenyi Feng.
 */
public class HexUtil {

    /**
     * 二进制转十六进制
     * @param bytes [ellipsis]
     * @return [ellipsis]
     */
    public static String _2_16(byte[] bytes) {

        if (bytes == null || bytes.length == 0)
            return null;

        StringBuilder hexStr = new StringBuilder();

        for (byte b : bytes) {
            if (b < 0) {
                b += 256;
            }
            if (b < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(b));
        }

        return hexStr.toString();
    }

}
