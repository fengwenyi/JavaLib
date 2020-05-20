package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.constant.ContentType;
import com.fengwenyi.javalib.constant.HttpHeaderKeyConstant;
import com.fengwenyi.javalib.constant.MediaTypeConstant;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * Http请求工具类
 * <ul>
 *     <li>get</li>
 *     <li>post</li>
 *     <li>form表单提交</li>
 *     <li>json提交</li>
 *     <li>header设置</li>
 * </ul>
 * @author Erwin Feng
 * @since 2019-06-27 00:09
 */
public class HttpUtils {

    /**
     * post方式提交表单
     * @param url URL
     * @param paramMap 参数，用Map进行封装
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String postForm(String url, Map<String, String> paramMap) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            formBuilder.add(key, value);
        }
        FormBody formBody = formBuilder.build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    /**
     * post方式提交json字符串格式参数
     * @param url URL
     * @param jsonStr 参数，json字符串
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String postJson(String url,  String jsonStr) throws IOException {
        MediaType mediaType = MediaType.parse(ContentType.JSON);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, jsonStr))
                .build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    /**
     * get请求
     * @param url URL
     * @param map 参数，用Map进行封装
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String get(String url, Map<String, String> map) throws IOException {
        String param = ParamUtils.getUrlParamsByMap(map);
        if (StringUtils.isNotEmpty(param))
            url = url + "?" + param;
        return get(url);
    }

    /**
     * get请求
     * @param url URL
     * @param param 参数
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String get(String url, String param) throws IOException {
        if (StringUtils.isNotEmpty(param))
            url = url + "?" + param;
        return get(url);
    }

    /**
     * get请求
     * @param url URL
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String get(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    /**
     * get请求
     * @param url URL
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String getJson(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HttpHeaderKeyConstant.CONTENT_TYPE, MediaTypeConstant.APPLICATION_JSON_VALUE)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    /**
     * get请求
     * @param url URL
     * @param param 参数
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    public static String getJson(String url, String param) throws IOException {
        if (StringUtils.isNotEmpty(param))
            url = url + "?" + param;
        return getJson(url);
    }

    /**
     * 自定义get请求
     * @param url           URL
     * @param headers       自定义请求头
     * @param param         参数
     * @return              服务器响应字符串
     * @throws IOException  IO异常
     */
    public static String get(String url, Map<String, String> headers, String param) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request.Builder requestBuilder = new Request.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String headerKey = entry.getKey();
            String headerValue = entry.getValue();
            requestBuilder.addHeader(headerKey, headerValue);
        }
        if (StringUtils.isNotEmpty(param))
            url = url + "?" + param;
        requestBuilder.url(url).get();
        Request request = requestBuilder.build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    /**
     * 自定义post请求
     * @param url           URL
     * @param headers       自定义请求头
     * @param param         参数
     * @return              服务器响应字符串
     * @throws IOException  IO异常
     */
    public static String post(String url, Map<String, String> headers, String param) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request.Builder requestBuilder = new Request.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String headerKey = entry.getKey();
            String headerValue = entry.getValue();
            requestBuilder.addHeader(headerKey, headerValue);
        }
        if (StringUtils.isNotEmpty(param))
            url = url + "?" + param;
        requestBuilder.url(url)
                .post(s);
        Request request = requestBuilder.build();
        Response response = okHttpClient.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

}
