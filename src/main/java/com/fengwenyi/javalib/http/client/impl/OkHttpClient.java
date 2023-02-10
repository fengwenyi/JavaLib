package com.fengwenyi.javalib.http.client.impl;

import com.fengwenyi.javalib.collection.MapUtils;
import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.http.Request;
import com.fengwenyi.javalib.http.Response;
import com.fengwenyi.javalib.http.client.HttpClient;
import okhttp3.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-02-09
 */
public class OkHttpClient implements HttpClient {
    @Override
    public Response execute(Request request, Request.Option option) throws IOException {
        if (Request.Method.GET == request.getMethod()) {
            return get(request, option);
        } else if (Request.Method.POST == request.getMethod()) {
            return post(request, option);
        }
        throw new RemoteException("request method '" + request.getMethod().name() + "' not implemented");
    }

    private okhttp3.OkHttpClient client(Request.Option option) {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.connectTimeout(Duration.ofSeconds(option.getConnectTimeoutSecond()));
        builder.readTimeout(Duration.ofSeconds(option.getReadTimeoutSecond()));
        return builder.build();
    }

    private Response get(Request request, Request.Option option) {
        okhttp3.OkHttpClient client = client(option);
        okhttp3.Request httpRequest = buildRequest(getHttpUrl(request), option.getHeaders(), request.getMethod(), null);
        okhttp3.Response httpResponse = call(client, httpRequest);
        return convertResponse(httpResponse);
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
                    requestBody = buildJsonRequestBody(JsonUtils.convertString(param));
                } else if (Request.ParamFormat.FORM == paramFormat) {
                    requestBody = buildFormRequestBody(param);
                }
            }
        }
        okhttp3.Request httpRequest = buildRequest(getHttpUrl(request), option.getHeaders(), request.getMethod(), requestBody);
        okhttp3.Response httpResponse = call(client, httpRequest);
        return convertResponse(httpResponse);
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

    private okhttp3.Request buildRequest(HttpUrl httpUrl, Map<String, String> headers, Request.Method requestMethod, RequestBody requestBody) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(httpUrl);
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

    private okhttp3.Response call(okhttp3.OkHttpClient client, okhttp3.Request request) {
        Call call = client.newCall(request);
        try (okhttp3.Response response = call.execute()) {
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response convertResponse(okhttp3.Response httpResponse) {
        if (Objects.isNull(httpResponse)) {
            throw new RuntimeException("http response null");
        }
        Response response = new Response();
        response.setCode(httpResponse.code());
        response.setMsg(httpResponse.message());
        ResponseBody responseBody = httpResponse.body();
        if (Objects.nonNull(responseBody)) {
            try {
                response.setBody(responseBody.string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

}
