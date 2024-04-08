package com.fengwenyi.javalib.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
public class HttpUtilsTests {

    @Test
    public void testGet() {
        System.out.println(HttpUtils.get("https://www.baidu.com"));
    }

    @Test
    public void testGetJsonResponse() {

        String url = "https://erwin-api.fengwenyi.com/erwin/bookmark/page?currentPage=1&pageSize=10";
        // System.out.println(HttpUtils.get(url));

        Request request = new Request();
        request.setUrl(url);
        request.setMethod(Request.Method.GET);

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "application/json");

        Request.Option option = new Request.Option();
        option.setHeaders(headerMap);

        try {
            System.out.println(HttpUtils.execute(request, option));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testFull() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("currentPage", 1);
        paramMap.put("pageSize", 10);

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "application/json");

        Request request = new Request();
        request.setUrl("https://erwin-api.fengwenyi.com/erwin/bookmark/page");
        request.setParam(paramMap);
        request.setMethod(Request.Method.GET);
        // request.setUtil(Request.Util.JDK);
        request.setUtil(Request.Util.OkHttp);

        Request.Option option = new Request.Option();
        option.setHeaders(headerMap);
        option.setConnectTimeoutSecond(3);
        option.setReadTimeoutSecond(5);
        option.setLogLevel(Request.LogLevel.DEBUG);

        try {
            String result = HttpUtils.execute(request, option);
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
