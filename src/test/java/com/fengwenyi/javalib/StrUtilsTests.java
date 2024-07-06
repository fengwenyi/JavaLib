package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.StrUtils;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author Erwin Feng
 * @since 2020/8/23 2:18 上午
 */
public class StrUtilsTests {

    @Test
    public void testGenerateStar() {
        String passwordStar = StrUtils.generateStar(32);
        Assert.assertEquals(passwordStar.length(), 32);
        System.out.println(passwordStar);
    }

    @Test
    public void testGetLeft() {
        String name = "张三";
        String result = StrUtils.getLeft(name, 0);
        Assert.assertEquals(result, "");
        System.out.println(result);

        result = StrUtils.getLeft(name, 1);
        Assert.assertEquals(result, "张");
        System.out.println(result);

        result = StrUtils.getLeft(name, 2);
        Assert.assertEquals(result, "张三");
        System.out.println(result);

        result = StrUtils.getLeft(name, 3);
        Assert.assertEquals(result, "张三");
        System.out.println(result);
    }

    @Test
    public void testGetRight() {
        String name = "张三";
        String result = StrUtils.getRight(name, 0);
        System.out.println(result);

        result = StrUtils.getRight(name, 1);
        Assert.assertEquals(result, "三");
        System.out.println(result);

        result = StrUtils.getRight(name, 2);
        Assert.assertEquals(result, "张三");
        System.out.println(result);

        result = StrUtils.getRight(name, 3);
        Assert.assertEquals(result, "张三");
        System.out.println(result);
    }

}
