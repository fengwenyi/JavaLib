package com.fengwenyi.javalib.http.client.impl;

import com.fengwenyi.javalib.collection.MapUtils;
import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.file.IOUtils;
import com.fengwenyi.javalib.http.Request;
import com.fengwenyi.javalib.http.Response;
import com.fengwenyi.javalib.http.client.HttpClient;
import com.fengwenyi.javalib.util.StringUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * JDK http 客户端
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public class JdkHttpClient implements HttpClient {

    @Override
    public Response execute(Request request, Request.Option option) throws IOException {
        String requestUrl = getUrl(request);
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection instanceof HttpsURLConnection) {
            HttpsURLConnection sslCon = (HttpsURLConnection) connection;
            SSLSocketFactory sslContextFactory = option.getSslContextFactory();
            HostnameVerifier hostnameVerifier = option.getHostnameVerifier();
            if (Objects.isNull(sslContextFactory)) {
                sslContextFactory = defaultSSLSocketFactory();
            }
            if (Objects.isNull(hostnameVerifier)) {
                hostnameVerifier = (s, sslSession) -> true;
            }
            sslCon.setSSLSocketFactory(sslContextFactory);
            sslCon.setHostnameVerifier(hostnameVerifier);
        }
        connection.setRequestMethod(request.getMethod().name());
        if (MapUtils.isNotEmpty(option.getHeaders())) {
            for (Map.Entry<String, String> entry : option.getHeaders().entrySet()) {
                if (Objects.nonNull(entry) && StringUtils.isNotEmpty(entry.getKey())) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
        connection.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(option.getConnectTimeoutSecond()));
        connection.setReadTimeout((int) TimeUnit.SECONDS.toMillis(option.getReadTimeoutSecond()));
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        if (Request.Method.POST == request.getMethod()
                && MapUtils.isNotEmpty(request.getParam())) {
            OutputStream outputStream = connection.getOutputStream();
            String param = "";
            if (Request.ParamFormat.JSON == request.getParamFormat()) {
                param = getParamJson(request);
            } else {
                param = getParamForm(request);
            }
            outputStream.write(param.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        }
        Response response = new Response();
        response.setCode(connection.getResponseCode());
        response.setMsg(connection.getResponseMessage());
        InputStream inputStream;
        if (connection.getResponseCode() == 200) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
        String body = IOUtils.readAndClose(inputStream);
        response.setBody(body);
        connection.disconnect();
        return response;
    }

    private static SSLSocketFactory defaultSSLSocketFactory() {
        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("SSL");
            TrustManager[] trustManagers = new TrustManager[1];
            trustManagers[0] = new DefaultTrustMannager();
            ctx.init(null, trustManagers, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return ctx.getSocketFactory();
    }

    private static final class DefaultTrustMannager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private String getUrl(Request request) {
        StringBuilder requestUrl = new StringBuilder(request.getUrl());
        if (Request.Method.GET == request.getMethod()
                && MapUtils.isNotEmpty(request.getParam())) {
            requestUrl.append("?").append(getParamForm(request));
        }
        return requestUrl.toString();
    }

    private String getParamJson(Request request) {
        if (request.getParam().containsKey(PARAM_DEFAULT_KEY)) {
            return request.getParam().get(PARAM_DEFAULT_KEY) + "";
        } else {
            return JsonUtils.convertString(request.getParam());
        }
    }

    private String getParamForm(Request request) {
        if (request.getParam().containsKey(PARAM_DEFAULT_KEY)) {
            return request.getParam().get(PARAM_DEFAULT_KEY) + "";
        } else {
            StringBuilder param = new StringBuilder();
            for (Map.Entry<String, Object> entry : request.getParam().entrySet()) {
                param.append(entry.getKey()).append("=").append(entry.getValue());
            }
            return param.toString();
        }
    }
}
