package com.fengwenyi.javalib.convert;

import com.fengwenyi.javalib.util.StrUtils;

/**
 * 进制转换工具类
 *
 * @author Wenyi Feng.
 */
public class HexUtils {

    /**
     * 二进制转十六进制
     *
     * @param bytes [ellipsis]
     * @return [ellipsis]
     */
    public static String _2_16(byte[] bytes) {

        if (bytes == null || bytes.length == 0)
            return null;

        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 16进制转化为 2进制
     *
     * @param hexStr 16进制字符串
     * @return byte[]
     */
    public static byte[] _16_2(String hexStr) {
        if (StrUtils.isBlank(hexStr))
            return null;

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}