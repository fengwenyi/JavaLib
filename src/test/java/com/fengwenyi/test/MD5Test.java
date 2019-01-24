package com.fengwenyi.test;

import com.fengwenyi.javalib.util.MD5Util;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * @author Wenyi Feng
 * @since 2019-01-24
 */
public class MD5Test {

    @Test
    public void md5() {
        String md5Str = null;
        try {
            md5Str = MD5Util.MD5("12345678");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5Str);
    }

}
