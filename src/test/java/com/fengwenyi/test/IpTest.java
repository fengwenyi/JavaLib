package com.fengwenyi.test;

import com.alibaba.fastjson.JSONObject;
import com.fengwenyi.javalib.constant.URL;
import com.fengwenyi.javalib.util.IpUtil;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Wenyi Feng
 * @since 2018-11-17
 */
public class IpTest {

    @Test
    public void getNetData() {
        String url = URL.IP_INFO_URI + "?ip=180.76.76.76";
        //MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
        //String param = JSON.toJSONString(obj);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .url(url).removeHeader("User-Agent").addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36 JavaLib").build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("返回参数：" + (response.body() == null ? "":response.body().string()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        JSONObject jsonObject = null;
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



}
