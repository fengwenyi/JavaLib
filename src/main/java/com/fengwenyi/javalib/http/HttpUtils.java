package com.fengwenyi.javalib.http;

import com.fengwenyi.javalib.http.client.HttpClient;
import com.fengwenyi.javalib.http.client.HttpClientFactory;

import java.io.*;
import java.util.Map;
import java.util.Objects;

/**
 * Http 工具类 <br><br>
 *
 * <p>简单示例：</p>
 *
 * <pre>
 *     String result = HttpUtils.get("https://www.baidu.com");
 * </pre>
 *
 * <p>完整示例：</p>
 *
 * <pre>
 *     Map&lt;String, Object&gt; paramMap = new HashMap&lt;&gt;();
 *     paramMap.put("currentPage", 1);
 *     paramMap.put("pageSize", 10);
 *
 *     Map&lt;String, String&gt; headerMap = new HashMap&lt;&gt;();
 *     headerMap.put("Accept", "application/json");
 *
 *     Request request = new Request();
 *     request.setUrl("https://erwin-api.fengwenyi.com/erwin/bookmark/page");
 *     request.setParam(paramMap);
 *     request.setMethod(Request.Method.GET);
 *     request.setUtil(Request.Util.JDK);
 *
 *     Request.Option option = new Request.Option();
 *     option.setHeaders(headerMap);
 *     option.setConnectTimeoutSecond(3);
 *     option.setReadTimeoutSecond(5);
 *     option.setLogLevel(Request.LogLevel.DEBUG);
 *
 *     try {
 *         String result = HttpUtils.execute(request, option);
 *     } catch (IOException e) {
 *         throw new RuntimeException(e);
 *     }
 * </pre>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-27
 */
public class HttpUtils {

    /**
     * http get 请求
     * @param url 地址
     * @param param 参数
     * @param headers http headers
     * @return 服务器响应结果
     */
    public  static String get(String url, String param, Map<String, String> headers) {
        try {
            Request request = buildRequest(url, param);
            request.setMethod(Request.Method.GET);
            Request.Option option = buildOption(null, null, headers);
            return execute(request, option);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * http get 请求
     * @param url 地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public static String get(String url, String param) {
        return get(url, "", null);
    }

    /**
     * http get 请求
     * @param url 地址
     * @return 服务器响应结果
     */
    public static String get(String url) {
        return get(url, "");
    }

    /**
     * http post 请求
     * @param url 地址
     * @param param 参数
     * @param headers http headers
     * @return 服务器响应结果
     */
    public  static String post(String url, String param, Map<String, String> headers) {
        try {
            Request request = buildRequest(url, param);
            request.setMethod(Request.Method.POST);
            Request.Option option = buildOption(null, null, headers);
            return execute(request, option);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * http post 请求
     * @param url 地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public  static String post(String url, String param) {
        return post(url, param, null);
    }

    /**
     * 构造 Request
     * @param url 地址
     * @param param 参数
     * @return Request
     */
    public static Request buildRequest(String url, String param) {
        return Request.create(url, null, param);
    }

    /**
     * 构造 get 请求方式的 Request
     * @param url 地址
     * @param param 参数
     * @return Request
     */
    public static Request buildGetRequest(String url, String param) {
        return Request.create(url, Request.Method.GET, param);
    }

    /**
     * 构造 get 请求方式的 Request
     * @param url 地址
     * @param param 参数
     * @return Request
     */
    public static Request buildPostRequest(String url, String param) {
        return Request.create(url, Request.Method.POST, param);
    }

    /**
     * 构造 Request.Option
     * @param connectTimeoutSecond 连接超时时间，秒
     * @param readTimeoutSecond 读取超时时间，秒
     * @param headers http headers
     * @return Request.Option
     */
    public static Request.Option buildOption(Integer connectTimeoutSecond, Integer readTimeoutSecond, Map<String, String> headers) {
        return Request.Option.create(connectTimeoutSecond, readTimeoutSecond, headers);
    }

    /**
     * 执行 HTTP 请求
     * @param request 请求相关参数, {@link Request}
     * @param option Http 请求属性, {@link Request.Option}
     * @return 服务器响应结果
     * @throws IOException IO读取异常, {@link IOException}
     */
    public static String execute(Request request, Request.Option option) throws IOException {
        check(request, option);
        HttpClient httpClient = HttpClientFactory.get(request.getUtil());
        Response response = httpClient.execute(request, option);
        return handleResponse(response);
    }

    private static String handleResponse(Response response) throws IOException {
        if (Objects.isNull(response)) {
            return "";
        }
        String data = response.getBody();
        if (response.getCode() == 200) {
            return data;
        }
        throw new RuntimeException(data);
    }

    private static void check(Request request, Request.Option option) {
        notNull(request, "request must be not null");
        notNull(option, "option must be not null");
        notNull(request.getUrl(), "url must be not null");
        notNull(request.getMethod(), "method must be not null");
        notNull(request.getUtil(), "http util must be not null");
    }

    private static void notNull(Object param, String message) {
        if (Objects.isNull(param)) {
            throw new RuntimeException(message);
        }
    }

}
