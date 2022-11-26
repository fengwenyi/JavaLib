package com.fengwenyi.javalib.http;

import com.fengwenyi.javalib.http.client.HttpClient;
import com.fengwenyi.javalib.http.client.HttpClientFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * Http 工具类 <br/><br/>
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
 *     Map<String, Object> paramMap = new HashMap<>();
 *     paramMap.put("currentPage", 1);
 *     paramMap.put("pageSize", 10);
 *
 *     Map<String, String> headerMap = new HashMap<>();
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

    public static String get(String url, String param) {
        return get(url, "", null);
    }

    public static String get(String url) {
        return get(url, "");
    }

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

    public  static String post(String url, String param) {
        return post(url, param, null);
    }

    public static Request buildRequest(String url, String param) {
        return Request.create(url, null, param);
    }

    public static Request buildGetRequest(String url, String param) {
        return Request.create(url, Request.Method.GET, param);
    }

    public static Request buildPostRequest(String url, String param) {
        return Request.create(url, Request.Method.POST, param);
    }

    public static Request.Option buildOption(Integer connectTimeout, Integer readTimeout, Map<String, String> headers) {
        return Request.Option.create(connectTimeout, readTimeout, headers);
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
        InputStream body = response.getBody().getBody();
        String data = readAndClose(body);
        if (response.getCode() == 200) {
            return data;
        }
        throw new RuntimeException(data);
    }

    private static String readAndClose(InputStream inputStream) throws IOException {
        BufferedReader reader;
        StringBuilder resultBuffer = new StringBuilder();
        String tempLine;

        reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        while ((tempLine = reader.readLine()) != null) {
            resultBuffer.append(tempLine);
        }
        inputStream.close();
        return resultBuffer.toString();
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
