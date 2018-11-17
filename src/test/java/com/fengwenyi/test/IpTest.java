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
        long startTime = System.nanoTime();
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=10.10.10.10";
        String result2= HttpUtil.get(url);
        System.out.println(result2);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000D;
        System.out.println("请求花费时间：" + time + "秒");
    }



}
