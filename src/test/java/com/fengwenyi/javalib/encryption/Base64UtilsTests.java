package com.fengwenyi.javalib.encryption;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-09-15
 */
public class Base64UtilsTests {

    @Test
    public void test() {
        String src = "hello，朋友";
        System.out.println("字符串：" + src);
        String encrypt = Base64Utils.encrypt(src.getBytes(StandardCharsets.UTF_8));
        System.out.println("加密串：" + encrypt);
        byte[] decode = Base64Utils.decode(encrypt);
        System.out.println("加密串：" + new String(decode));
    }

}
