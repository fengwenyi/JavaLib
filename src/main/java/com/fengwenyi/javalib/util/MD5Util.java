package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.constant.EncryptionType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 * @author Wenyi Feng
 * @since 2018-10-16
 */
public class MD5Util {

    /**
     * 32位大写
     * @param plainText [ellipsis]
     * @return [ellipsis]
     * @throws NoSuchAlgorithmException [ellipsis]
     */
    public static String MD5(String plainText) throws NoSuchAlgorithmException {
        // 创建一个md5算法对象
        MessageDigest md = MessageDigest.getInstance(EncryptionType.MD5);
        byte[] messageByte = plainText.getBytes();
        // 获得MD5字节数组,16*8=128位
        byte[] md5Byte = md.digest(messageByte);
        // 转换为16进制字符串
        return HexUtil._2_16(md5Byte);
    }

}
