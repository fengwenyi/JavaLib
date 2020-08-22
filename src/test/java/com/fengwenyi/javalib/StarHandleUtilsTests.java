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

}
