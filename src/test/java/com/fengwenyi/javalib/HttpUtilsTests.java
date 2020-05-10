package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.HttpUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Erwin Feng
 * @since 2020/5/10 11:27 下午
 */
public class HttpUtilsTests {

    @Test
    public void testGet() throws IOException {
        String url = "https://www.baidu.com";
        String response = HttpUtils.get(url);
        System.out.println(response);
    }

    @Test
    public void testGetJson() throws IOException {
        String url = "http://localhost:9191/api/test/result";
        String response = HttpUtils.getJson(url);
        System.out.println(response);
    }

    @Test
    public void testGetParamMap() throws IOException {
        String url = "https://www.baidu.com";
        Map<String, String> map = new HashMap<>();
        String response = HttpUtils.get(url, map);
        System.out.println(response);
    }

    @Test
    public void testGetParam() throws IOException {
        String url = "https://www.baidu.com";
        String param = "";
        String response = HttpUtils.get(url, param);
        System.out.println(response);
    }

}
