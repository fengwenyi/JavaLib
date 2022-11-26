package com.fengwenyi.javalib.http;

import com.fengwenyi.javalib.collection.MapUtils;
import com.fengwenyi.javalib.util.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
public class Request {

    private String url;

    private Method method;

    private String param;

    private Util util = Util.JDK;

    public enum Method {
        GET, POST, PUT, PATCH, DELETE
    }

    public enum Util {
        JDK, OkHttp, AsyncHttpClient
    }

    public enum LogLevel {

        DEBUG, INFO, ERROR

    }

    public static Request create(String url, Method method, String param) {
        Request request = new Request();
        request.setUrl(url);
        request.setMethod(method);
        request.setParam(param);
        return request;
    }

    public static class Option {

        private Integer connectTimeoutSecond = 5;
        private Integer readTimeoutSecond = 45;
        private Map<String, String> headers = new HashMap<>();
        private SSLSocketFactory sslContextFactory;
        private HostnameVerifier hostnameVerifier;
        /** 日志级别 */
        private LogLevel logLevel;

        public static Option create(Integer connectTimeoutSecond, Integer readTimeoutSecond) {
            Option option = new Option();
            Optional.ofNullable(connectTimeoutSecond).ifPresent(option::setConnectTimeoutSecond);
            Optional.ofNullable(readTimeoutSecond).ifPresent(option::setReadTimeoutSecond);
            return option;
        }

        public static Option create(Integer connectTimeoutSecond, Integer readTimeoutSecond, Map<String, String> headers) {
            Option option = new Option();
            Optional.ofNullable(connectTimeoutSecond).ifPresent(option::setConnectTimeoutSecond);
            Optional.ofNullable(readTimeoutSecond).ifPresent(option::setReadTimeoutSecond);
            Optional.ofNullable(headers).ifPresent(option::setHeaders);
            return option;
        }

        public void setSslContextFactory(SSLSocketFactory sslContextFactory) {
            this.sslContextFactory = sslContextFactory;
        }

        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
        }

        public Integer getConnectTimeoutSecond() {
            return connectTimeoutSecond;
        }

        public void setConnectTimeoutSecond(Integer connectTimeoutSecond) {
            this.connectTimeoutSecond = connectTimeoutSecond;
        }

        public Integer getReadTimeoutSecond() {
            return readTimeoutSecond;
        }

        public void setReadTimeoutSecond(Integer readTimeoutSecond) {
            this.readTimeoutSecond = readTimeoutSecond;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public SSLSocketFactory getSslContextFactory() {
            return sslContextFactory;
        }

        public HostnameVerifier getHostnameVerifier() {
            return hostnameVerifier;
        }

        public LogLevel getLogLevel() {
            return logLevel;
        }

        public void setLogLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getParam() {
        return param;
    }

    public Request setParam(String param) {
        this.param = param;
        return this;
    }

    public Request setParam(Map<String, Object> map) {
        if (MapUtils.isEmpty(map)) {
            return this;
        }
        StringJoiner stringJoiner = new StringJoiner("&", "", "");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getKey())) {
                stringJoiner.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        String param = stringJoiner.toString();
        return setParam(param);
    }

    public Util getUtil() {
        return util;
    }

    public void setUtil(Util util) {
        this.util = util;
    }
}
