package com.fengwenyi.javalib.encryption;

import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-09-15
 */
public class Base64UtilsTests {

    @Test
    public void test() {
        String src = "hello，朋友";
        PrintUtils.info("字符串：" + src);
        String encrypt = Base64Utils.encrypt(src.getBytes(StandardCharsets.UTF_8));
        PrintUtils.info("加密串：" + encrypt);
        byte[] decode = Base64Utils.decode(encrypt);
        PrintUtils.info("加密串：" + new String(decode));
    }

}
