package com.fengwenyi.javalib.http.client;

import com.fengwenyi.javalib.http.Request;
import com.fengwenyi.javalib.http.client.HttpClient;
import com.fengwenyi.javalib.http.client.impl.JdkHttpClient;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public class HttpClientFactory {

    public static HttpClient get(Request.Util httpUtil) {
        if (Request.Util.JDK == httpUtil) {
            return new JdkHttpClient();
        } else {
            throw new RuntimeException("not find http util: " + httpUtil.name());
        }
    }

}
