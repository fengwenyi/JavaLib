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
        StringBuilder hexStr = new StringBuilder();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }

}
