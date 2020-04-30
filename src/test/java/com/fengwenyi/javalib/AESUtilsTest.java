package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.AESUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Test;

/**
 * @author ycq
 * @since 2019-06-03
 */
public class AESUtilsTest {

    @Test
    public void AesTest(){
        String content = "123456";
        String password = "jsdz1234";
        PrintUtils.info("加密之前：" + content);
        // 加密
        String encrypt = AESUtils.encrypt(content, password);
        PrintUtils.info("加密后的内容：" + encrypt);
        // 解密
        String decrypt = AESUtils.decrypt(encrypt, password);
        PrintUtils.info("解密后的内容：" + decrypt);
    }
}
