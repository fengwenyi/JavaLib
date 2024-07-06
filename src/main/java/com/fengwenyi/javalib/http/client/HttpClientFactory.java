package com.fengwenyi.javalib.http.client;

import com.fengwenyi.javalib.http.Request;
import com.fengwenyi.javalib.http.client.impl.OkHttpClient;

/**
 * HTTP CLIENT 工厂
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public class HttpClientFactory {

    /**
     * 获取 http client
     *
     * @param httpUtil http 工具
     * @return http client 实现
     */
    public static HttpClient get(Request.Util httpUtil) {
        if (Request.Util.OkHttp == httpUtil) {
            return new OkHttpClient();
        } else {
            throw new RuntimeException("not find http util: " + httpUtil.name());
        }
    }

}
