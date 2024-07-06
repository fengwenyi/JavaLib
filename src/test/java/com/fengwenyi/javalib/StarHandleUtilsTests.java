package com.fengwenyi.javalib;

import com.fengwenyi.javalib.handle.StarHandleUtils;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

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
        System.out.println(result);

        realName = "张三丰";
        result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "张*丰");
        System.out.println(result);

        realName = "丰";
        result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "*");
        System.out.println(result);

        realName = "那些旋律依旧是那么完bai美■";
        result = StarHandleUtils.realName(realName);
        Assert.assertEquals(result, "那*************■");
        System.out.println(result);

    }

    @Test
    public void testPhone() {
        String phone = "12345678901";
        String result = StarHandleUtils.phone(phone);
        Assert.assertEquals(result, "123****8901");
        System.out.println(result);
    }

    @Test
    public void testIp() {
        String ip = "127.0.0.1";
        String result = StarHandleUtils.ip(ip);
        Assert.assertEquals(result, "127.0.*.*");
        System.out.println(result);
    }

    @Test
    public void testEmail() {
        String email = "zhangsan@163.com";
        String result = StarHandleUtils.email(email);
        Assert.assertEquals(result, "z*******@163.com");
        System.out.println(result);

        email = "zs@163.com";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "z*@163.com");

        email = "zsy@163.com";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "z**@163.com");

        email = "z@163.com";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "*@163.com");

        email = "@163.com";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "");

        email = "zhangsan@.com";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "z*******@.com");

        email = "zhangsan@1.com";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "z*******@1.com");

        email = "zhangsan@";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "z*******@");

        email = "zhangsan";
        result = StarHandleUtils.email(email);
        System.out.println(result);
        Assert.assertEquals(result, "");

    }

    @Test
    public void testIdCardNo() {
        String idCardNo15 = "320311770706001";
        String idCardNo18 = "130701199310302288";
        String idCardNo18x = "52030219891209794X";
        String star15 = StarHandleUtils.idCardNo(idCardNo15);
        System.out.println(star15);
        Assert.assertEquals(star15, "320********6001");

        String star18 = StarHandleUtils.idCardNo(idCardNo18, 5, 5);
        System.out.println(star18);
        Assert.assertEquals(star18, "13070********02288");

        String star18x = StarHandleUtils.idCardNo(idCardNo18x);
        System.out.println(star18x);
        Assert.assertEquals(star18x, "520***********794X");
    }

}
