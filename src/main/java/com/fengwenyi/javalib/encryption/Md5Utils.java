package com.fengwenyi.javalib.encryption;


import com.fengwenyi.javalib.constant.EncryptionTypeConstant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 * @author Wenyi Feng
 * @since 2018-10-16
 */
public class Md5Utils {

    /**
     * MD5加密
     * @param plainText 待加密的字符串
     * @return MD5加密之后的字符串，32位小写
     * @throws NoSuchAlgorithmException 算法失败
     */
    public static String encrypt(String plainText) throws NoSuchAlgorithmException {
        // 创建一个md5算法对象
        MessageDigest md = MessageDigest.getInstance(EncryptionTypeConstant.MD5);
        byte[] messageByte = plainText.getBytes();
        // 获得MD5字节数组,16*8=128位
        byte[] md5Byte = md.digest(messageByte);
        // 转换为16进制字符串

        StringBuilder sb = new StringBuilder();
        // 把每一个byte 做一个与运算 0xff;
        for (byte b : md5Byte) {
            // 与运算
            int number = b & 0xff;// 加盐
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                sb.append("0");
            }
            sb.append(str);
        }
        //32位加密
        return sb.toString();
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     * @param str 待加密字符串/加密后的字符串
     * @return 加密后的字符串/解密后的字符串
     */
    public static String convert(String str) {

        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);

    }


}
