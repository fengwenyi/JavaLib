package com.fengwenyi.javalib.http;

import com.fengwenyi.javalib.http.client.HttpClient;
import com.fengwenyi.javalib.http.client.HttpClientFactory;

import java.io.IOException;
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
     *
     * @param url 地址
     * @return 服务器响应结果
     */
    public static String get(String url) {
        return get(url, "");
    }

    /**
     * http get 请求
     *
     * @param url   地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public static String get(String url, String param) {
        try {
            Request request = Request.create(url, Request.Method.GET, param);
            return execute(request, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * http get 请求
     *
     * @param url   地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public static String get(String url, Map<String, Object> param) {
        try {
            Request request = Request.create(url, Request.Method.GET, param);
            return execute(request, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * http post 请求
     *
     * @param url   地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public static String postJson(String url, String param) {
        try {
            Request request = Request.create(url, Request.Method.POST, param);
            request.setParamFormat(Request.ParamFormat.JSON);
            return execute(request, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * http post 请求
     *
     * @param url   地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public static String postJson(String url, Map<String, Object> param) {
        try {
            Request request = Request.create(url, Request.Method.POST, param);
            request.setParamFormat(Request.ParamFormat.JSON);
            return execute(request, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * http post 请求
     *
     * @param url   地址
     * @param param 参数
     * @return 服务器响应结果
     */
    public static String postForm(String url, Map<String, Object> param) {
        try {
            Request request = Request.create(url, Request.Method.POST, param);
            request.setParamFormat(Request.ParamFormat.FORM);
            return execute(request, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构造 Request.Option
     *
     * @param connectTimeoutSecond 连接超时时间，秒
     * @param readTimeoutSecond    读取超时时间，秒
     * @param headers              http headers
     * @return Request.Option
     */
    public static Request.Option buildOption(Integer connectTimeoutSecond, Integer readTimeoutSecond, Map<String, String> headers) {
        return Request.Option.create(connectTimeoutSecond, readTimeoutSecond, headers);
    }

    /**
     * 执行 HTTP 请求
     *
     * @param request 请求相关参数, {@link Request}
     * @param option  Http 请求属性, {@link Request.Option}
     * @return 服务器响应结果
     * @throws IOException IO读取异常, {@link IOException}
     */
    public static String execute(Request request, Request.Option option) throws IOException {
        check(request);
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

    private static void check(Request request) {
        notNull(request, "request must be not null");
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
