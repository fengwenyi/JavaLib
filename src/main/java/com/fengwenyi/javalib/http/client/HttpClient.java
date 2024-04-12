package com.fengwenyi.javalib.http.client;

import com.fengwenyi.javalib.http.Request;
import com.fengwenyi.javalib.http.Response;
import com.fengwenyi.javalib.util.StrUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * HTTP 请求客户端 接口
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public interface HttpClient {

    String PARAM_DEFAULT_KEY = "__default__";

    /**
     * 执行 http 请求
     * @param request 请求
     * @param option http 可选配置
     * @return http 响应结果
     * @throws IOException IO 异常
     */
    Response execute(Request request, Request.Option option) throws IOException;

    default Integer getTimeoutSecond(Integer timeoutSecond) {
        if (Objects.nonNull(timeoutSecond) && timeoutSecond > 0) {
            return timeoutSecond;
        }
        return null;
    }

}
