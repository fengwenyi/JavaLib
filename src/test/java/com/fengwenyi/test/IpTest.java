package com.fengwenyi.test;

import com.fengwenyi.javalib.util.HttpUtil;
import com.fengwenyi.javalib.util.IpUtil;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Wenyi Feng
 * @since 2018-11-17
 */
public class IpTest {

    @Test
    public void test1() {
        JsonObject jsonObject = null;
        try {
            jsonObject = IpUtil.getIpInfo("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);
    }

    @Test
    public void test2() {
        try {
            String rs = IpUtil.getPosition("1");
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        String result2= null;
        try {
            result2 = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result2);
    }



}
