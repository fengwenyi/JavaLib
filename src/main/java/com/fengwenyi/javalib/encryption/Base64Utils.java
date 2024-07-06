package com.fengwenyi.javalib.encryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64加解密工具
 *
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-09-15
 */
public class Base64Utils {

    /**
     * 加密
     *
     * @param src 待加密的字节数组
     * @return 加密串
     */
    public static String encrypt(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    /**
     * 解密
     *
     * @param src 加密字符串
     * @return 字节数组
     */
    public static byte[] decode(String src) {
        return Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8));
    }

}
