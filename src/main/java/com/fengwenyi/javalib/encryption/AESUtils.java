package com.fengwenyi.javalib.encryption;

import com.fengwenyi.javalib.util.HexUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密解密工具类
 * @author ycq
 * @since 2019-06-03
 */
public class AESUtils {

    /**
     * AES专用密钥
     */
    private static SecretKeySpec key;

    /**
     * 密码器
     */
    private static Cipher cipher;

    /**
     * 加密/解密公共部分
     * @param password
     */
    private static void Common(String password){
        try {
            KeyGenerator kgen = null;// 创建AES的Key生产者
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。
            key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            cipher = Cipher.getInstance("AES");// 创建密码器
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * AES加密字符串
     * @param content 需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文
     */
    public static String encrypt(String content, String password) {
        try {
            Common(password);
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return HexUtils._2_16(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     * @param content AES加密过过的内容
     * @param password 加密时的密码
     * @return 明文
     */
    public static String decrypt(String content, String password) {
        try {
            Common(password);
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(HexUtils._16_2(content));
            return new String(result); // 明文
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
