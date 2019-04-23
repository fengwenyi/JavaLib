package com.fengwenyi.test;

import com.fengwenyi.javalib.util.HexUtil;
import org.junit.Test;

/**
 * @author Wenyi Feng
 * @since 2018-10-16
 */
public class HexUtilTest {

    @Test
    public void _2_16_test() {
        String rs = HexUtil._2_16("1111".getBytes());
        System.out.println(rs);
    }
}
