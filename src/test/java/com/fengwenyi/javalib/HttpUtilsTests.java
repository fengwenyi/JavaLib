package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.HttpUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试HTTP请求工具类：HttpUtils
 * <p>测试项：</p>
 * <ul>
 *     <li>get请求</li>
 *     <li>post请求</li>
 *     <li>put请求</li>
 *     <li>patch请求</li>
 *     <li>delete请求</li>
 *     <li>json请求</li>
 *     <li>自定义header请求</li>
 * </ul>
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
