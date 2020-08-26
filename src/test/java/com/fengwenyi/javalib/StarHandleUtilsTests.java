package com.fengwenyi.javalib;

import com.fengwenyi.javalib.handle.StarHandleUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Erwin Feng
 * @since 2020/8/23 4:34 上午
 */
public class StarHandleUtilsTests {

    @Test
    public void testRealName() {
        String realName = "张三";
        String result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "*三");
        PrintUtils.info(result);

        realName = "张三丰";
        result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "张*丰");
        PrintUtils.info(result);

        realName = "丰";
        result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "*");
        PrintUtils.info(result);

        realName = "那些旋律依旧是那么完bai美■";
        result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "那*************■");
        PrintUtils.info(result);

    }

    @Test
    public void testPhone() {
        String phone = "12345678901";
        String result = StarHandleUtils.phone(phone);
        Assert.assertEquals(result, "123****8901");
        PrintUtils.info(result);
    }

    @Test
    public void testIp() {
        String ip = "127.0.0.1";
        String result = StarHandleUtils.ip(ip);
        Assert.assertEquals(result, "127.0.*.*");
        PrintUtils.info(result);
    }

    @Test
    public void testEmail() {
        String email = "zhangsan@163.com";
        String result = StarHandleUtils.email(email);
        Assert.assertEquals(result, "z*******@163.com");
        PrintUtils.info(result);

        email = "zs@163.com";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "z*@163.com");

        email = "zsy@163.com";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "z**@163.com");

        email = "z@163.com";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "*@163.com");

        email = "@163.com";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "");

        email = "zhangsan@.com";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "z*******@.com");

        email = "zhangsan@1.com";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "z*******@1.com");

        email = "zhangsan@";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "z*******@");

        email = "zhangsan";
        result = StarHandleUtils.email(email);
        PrintUtils.info(result);
        Assert.assertEquals(result, "");

    }

}
