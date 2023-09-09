package com.fengwenyi.javalib.http;

import com.fengwenyi.javalib.collection.MapUtils;
import com.fengwenyi.javalib.util.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * 请求所需要参数
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
public class Request {

    private static final String PARAM_DEFAULT_KEY = "__default__";

    /** 请求地址 */
    private String url;

    /** 请求方式 */
    private Method method;

    /** 请求参数 */
    private Map<String, Object> param;

    private ParamFormat paramFormat = ParamFormat.STRING;

    /** 请求工具 */
    private Util util = Util.JDK;

    public ParamFormat getParamFormat() {
        return paramFormat;
    }

    public void setParamFormat(ParamFormat paramFormat) {
        this.paramFormat = paramFormat;
    }

    public enum ParamFormat {
        STRING, FORM, JSON
    }

    /**
     * 请求方法枚举
     */
    public enum Method {
        GET, POST, PUT, PATCH, DELETE
    }

    /**
     * 请求工具枚举
     */
    public enum Util {
        JDK, OkHttp, //AsyncHttpClient
    }

    /**
     * 日志级别枚举
     */
    public enum LogLevel {

        DEBUG, INFO, ERROR

    }

    /**
     * 创建 Request
     * @param url 请求地址
     * @param method 请求方法
     * @param param 请求参数
     * @return Request
     */
    public static Request create(String url, Method method, String param) {
        Request request = new Request();
        request.setUrl(url);
        request.setMethod(method);
        request.setParam(param);
        return request;
    }

    /**
     * 创建 Request
     * @param url 请求地址
     * @param method 请求方法
     * @param param 请求参数
     * @return Request
     */
    public static Request create(String url, Method method, Map<String, Object> param) {
        Request request = new Request();
        request.setUrl(url);
        request.setMethod(method);
        request.setParam(param);
        return request;
    }

    /**
     * 请求可选参数
     */
    public static class Option {

        /** 连接超时时间，秒，默认5秒 */
        private Integer connectTimeoutSecond = 5;

        /** 读取超时时间，秒，默认45秒 */
        private Integer readTimeoutSecond = 45;

        /** 请求头 */
        private Map<String, String> headers = new HashMap<>();

        /** ssl */
        private SSLSocketFactory sslContextFactory;

        /** HostnameVerifier */
        private HostnameVerifier hostnameVerifier;

        /** 日志级别 */
        private LogLevel logLevel;

        /**
         * 创建 Request.Option
         * @param connectTimeoutSecond 连接超时时间，秒
         * @param readTimeoutSecond 读取超时时间，秒
         * @return Request.Option
         */
        public static Option create(Integer connectTimeoutSecond, Integer readTimeoutSecond) {
            Option option = new Option();
            Optional.ofNullable(connectTimeoutSecond).ifPresent(option::setConnectTimeoutSecond);
            Optional.ofNullable(readTimeoutSecond).ifPresent(option::setReadTimeoutSecond);
            return option;
        }

        /**
         * 创建 Request.Option
         * @param connectTimeoutSecond 连接超时时间，秒
         * @param readTimeoutSecond 读取超时时间，秒
         * @param headers http headers
         * @return Request.Option
         */
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

    public Map<String, Object> getParam() {
        return param;
    }

    public Request setParam(String param) {
        if (StringUtils.isNotEmpty(param)) {
            Map<String, Object> map = new HashMap<>();
            map.put(PARAM_DEFAULT_KEY, param);
            this.param = map;
        }
        return this;
    }

    public Request setParam(Map<String, Object> map) {
        this.param = map;
        return this;
    }

    public Request bak(Map<String, Object> map) {
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
