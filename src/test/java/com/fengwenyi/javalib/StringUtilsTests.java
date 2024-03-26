package com.fengwenyi.javalib;

import com.fengwenyi.javalib.constant.LengthConstant;
import com.fengwenyi.javalib.util.PrintUtils;
import com.fengwenyi.javalib.util.StringUtils;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author Erwin Feng
 * @since 2020/8/23 2:18 上午
 */
public class StringUtilsTests {

    @Test
    public void testGenerateStar() {
        String passwordStar = StringUtils.generateStar(LengthConstant.PASSWORD);
        Assert.assertEquals(passwordStar.length(), LengthConstant.PASSWORD);
        PrintUtils.info(passwordStar);
    }

    @Test
    public void testGetLeft() {
        String name = "张三";
        String result = StringUtils.getLeft(name, 0);
        Assert.assertEquals(result, "");
        PrintUtils.info(result);

        result = StringUtils.getLeft(name, 1);
        Assert.assertEquals(result, "张");
        PrintUtils.info(result);

        result = StringUtils.getLeft(name, 2);
        Assert.assertEquals(result, "张三");
        PrintUtils.info(result);

        result = StringUtils.getLeft(name, 3);
        Assert.assertEquals(result, "张三");
        PrintUtils.info(result);
    }

    @Test
    public void testGetRight() {
        String name = "张三";
        String result = StringUtils.getRight(name, 0);
        PrintUtils.info(result);

        result = StringUtils.getRight(name, 1);
        Assert.assertEquals(result, "三");
        PrintUtils.info(result);

        result = StringUtils.getRight(name, 2);
        Assert.assertEquals(result, "张三");
        PrintUtils.info(result);

        result = StringUtils.getRight(name, 3);
        Assert.assertEquals(result, "张三");
        PrintUtils.info(result);
    }

}
