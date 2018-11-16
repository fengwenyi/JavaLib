package com.fengwenyi.test;

import com.fengwenyi.javalib.util.ExceptionUtil;
import org.junit.Test;

/**
 * 异常工具类测试
 * @author Wenyi Feng
 * @since 2018-11-17
 */
public class ExceptionUtilTest {

    // 要求参数不为空测试
    @Test
    public void paramNotNull() {
        //ExceptionUtil.isNotNull("123"); // 无异常

        /*
        异常：java.lang.IllegalArgumentException: Param must not null.
         */
        //ExceptionUtil.isNotNull(null);
    }

    // 要求参数为空测试
    @Test
    public void paramNull() {
        /*
        异常：java.lang.IllegalArgumentException: Param must null.
         */
        //ExceptionUtil.isNull("123");


        ExceptionUtil.isNull(null); // 无异常
    }

}
