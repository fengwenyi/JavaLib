package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.constant.EncryptionType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA加密算法工具类
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class SHAUtils {

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
        if (StringUtils.isNotEmpty(plainText)) {
            // SHA 加密开始
            // 创建加密对象 并传入加密类型
            MessageDigest messageDigest = MessageDigest.getInstance(EncryptionType.SHA1);
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

}
