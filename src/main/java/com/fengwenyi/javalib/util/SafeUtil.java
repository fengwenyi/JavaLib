package com.fengwenyi.javalib.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Safe 加密
 * @author Wenyi Feng.
 */
public class SafeUtil {

    /**
     * 字符串 SHA 加密
     * @param plainText [ellipsis]
     * @return [ellipsis]
     * @throws NoSuchAlgorithmException [ellipsis]
     */
    public static String SHA1(String plainText) throws NoSuchAlgorithmException {
        // 返回值
        String result = null;
        // 是否是有效字符串
        if (!StringUtil.isNullStr(plainText)) {
            // SHA 加密开始
            // 创建加密对象 并传入加密类型
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            // 传入要加密的字符串
            messageDigest.update(plainText.getBytes());
            // 得到 byte 类型结果
            byte byteBuffer[] = messageDigest.digest();
            // 将 byte 转换为 string
            StringBuilder strHexString = new StringBuilder();
            // 遍历 byte buffer
            for (int i = 0; i < byteBuffer.length; i++) {
                String hex = Integer.toHexString(0xff & byteBuffer[i]);
                if (hex.length() == 1) {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
            // 得到返回结果
            result = strHexString.toString();
        }
        return result;
    }

    /**
     * MD5加密
     * @param plainText [ellipsis]
     * @return [ellipsis]
     * @throws NoSuchAlgorithmException [ellipsis]
     */
    public static String MD5(String plainText) throws NoSuchAlgorithmException {
        // 创建一个md5算法对象
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageByte = plainText.getBytes();
        // 获得MD5字节数组,16*8=128位
        byte[] md5Byte = md.digest(messageByte);
        // 转换为16进制字符串
        return HexUtil._2_16(md5Byte);
    }

}
