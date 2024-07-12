package com.fengwenyi.javalib.http.client.impl;

import com.fengwenyi.javalib.collection.CollectionUtils;
import com.fengwenyi.javalib.collection.MapUtils;
import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.http.Request;
import com.fengwenyi.javalib.http.Response;
import com.fengwenyi.javalib.http.client.HttpClient;
import com.fengwenyi.javalib.util.StrUtils;
import okhttp3.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-02-09
 */
public class OkHttpClient implements HttpClient {
    @Override
    public Response execute(Request request, Request.Option option) throws IOException {
        if (CollectionUtils.isNotEmpty(request.getFileList())) {
            return upload(request, option);
        }
        if (Request.Method.GET == request.getMethod()) {
            return get(request, option);
        } else if (Request.Method.POST == request.getMethod()) {
            return post(request, option);
        }
        throw new RemoteException("request method '" + request.getMethod().name() + "' not implemented");
    }

    private okhttp3.OkHttpClient client(Request.Option option) {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        if (Objects.nonNull(option)) {
            Integer connectTimeoutSecond = getTimeoutSecond(option.getConnectTimeoutSecond());
            if (Objects.nonNull(connectTimeoutSecond)) {
                builder.connectTimeout(Duration.ofSeconds(connectTimeoutSecond));
            }
            Integer readTimeoutSecond = getTimeoutSecond(option.getReadTimeoutSecond());
            if (Objects.nonNull(readTimeoutSecond)) {
                builder.readTimeout(Duration.ofSeconds(readTimeoutSecond));
            }
        }
        return builder.build();
    }

    private Response get(Request request, Request.Option option) {
        okhttp3.OkHttpClient client = client(option);
        okhttp3.Request httpRequest = buildRequest(getUrl(request), option.getHeaders(), request.getMethod(), null);
        return call(client, httpRequest);
    }

    private Response post(Request request, Request.Option option) {
        okhttp3.OkHttpClient client = client(option);
        Request.ParamFormat paramFormat = request.getParamFormat();
        Map<String, Object> param = request.getParam();
        RequestBody requestBody = null;
        if (MapUtils.isNotEmpty(param)) {
            if (param.containsKey(PARAM_DEFAULT_KEY)) {
                String paramStr = param.get(PARAM_DEFAULT_KEY) + "";
                if (Request.ParamFormat.JSON == paramFormat) {
                    requestBody = buildJsonRequestBody(paramStr);
                }
            } else {
                if (Request.ParamFormat.JSON == paramFormat) {
                    requestBody = buildJsonRequestBody(JsonUtils.string(param));
                } else if (Request.ParamFormat.FORM == paramFormat) {
                    requestBody = buildFormRequestBody(param);
                }
            }
        }
        okhttp3.Request httpRequest = buildRequest(getUrl(request), option.getHeaders(), request.getMethod(), requestBody);
        return call(client, httpRequest);
    }

    private RequestBody buildJsonRequestBody(String param) {
        return RequestBody.create(
                param,
                MediaType.parse("application/json")
        );
    }

    private RequestBody buildFormRequestBody(Map<String, Object> param) {
        FormBody.Builder builder = new FormBody.Builder();
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                builder.add(entry.getKey(), entry.getValue() + "");
            }
        }
        return builder.build();
    }

    private okhttp3.Request buildRequest(String url, Map<String, String> headers, Request.Method requestMethod, RequestBody requestBody) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(url);
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (Objects.nonNull(requestBody)) {
            if (Request.Method.POST == requestMethod) {
                builder.post(requestBody);
            } else if (Request.Method.PUT == requestMethod) {
                builder.put(requestBody);
            }
        }
        return builder.build();
    }

    private Response call(okhttp3.OkHttpClient client, okhttp3.Request request) {
        Call call = client.newCall(request);
        try (okhttp3.Response httpResponse = call.execute()) {
            return convertResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response convertResponse(okhttp3.Response httpResponse) throws IOException {
        if (Objects.isNull(httpResponse)) {
            throw new RuntimeException("http response null");
        }
        Response response = new Response();
        response.setCode(httpResponse.code());
        response.setMsg(httpResponse.message());
        ResponseBody responseBody = httpResponse.body();
        if (Objects.nonNull(responseBody)) {
            response.setBody(responseBody.string());
        }
        return response;
    }

    private HttpUrl getHttpUrl(Request request) {
        HttpUrl.Builder builder = HttpUrl.get(request.getUrl()).newBuilder();

        if (Request.Method.GET == request.getMethod()) {
            if (MapUtils.isNotEmpty(request.getParam())) {
                if (request.getParam().containsKey(PARAM_DEFAULT_KEY)) {
                    builder.addPathSegment(request.getParam().get(PARAM_DEFAULT_KEY) + "");
                } else {
                    for (Map.Entry<String, Object> entry : request.getParam().entrySet()) {
                        builder.addQueryParameter(entry.getKey(), entry.getValue() + "");
                    }
                }
            }
        }

        return builder.build();
    }

    private String getUrl(Request request) {

        if (StrUtils.isBlank(request.getUrl())) {
            throw new RuntimeException("url不能为空");
        }

        StringBuilder url = new StringBuilder(request.getUrl());

        if (Request.Method.GET == request.getMethod()) {
            if (!url.toString().contains("?")) {
                url.append("?");
            }
            if (MapUtils.isNotEmpty(request.getParam())) {
                if (request.getParam().containsKey(PARAM_DEFAULT_KEY)) {
                    url.append(request.getParam().get(PARAM_DEFAULT_KEY));
                } else {
                    for (Map.Entry<String, Object> entry : request.getParam().entrySet()) {
                        url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                }
            }
        }

        String result = url.toString();
        if (result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 文件上传
     */
    private Response upload(Request request, Request.Option option) {
        okhttp3.OkHttpClient client = client(option);

        // 创建 MediaType 对象
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");


        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);

        List<Request.FileBo> fileList = request.getFileList();
        for (Request.FileBo fileBo : fileList) {
            bodyBuilder.addFormDataPart(fileBo.getParamName(), fileBo.getFileName(), RequestBody.create(mediaType, fileBo.getFile()));
        }
        if (MapUtils.isNotEmpty(request.getParam())) {
            for (Map.Entry<String, Object> entry : request.getParam().entrySet()) {
                bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue() + "");
            }
        }
        RequestBody requestBody = bodyBuilder.build();

        okhttp3.Request httpRequest = buildRequest(getUrl(request), option.getHeaders(), request.getMethod(), requestBody);
        return call(client, httpRequest);
    }

}
