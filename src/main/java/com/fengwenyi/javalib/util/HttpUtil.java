package com.fengwenyi.javalib.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Http工具类，支持：get / post / put / delete
 * @author Wenyi Feng
 */
public class HttpUtil {

    // httpUrlConn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");//表单上传的模式
    // httpURLConnection.setRequestProperty("Content-Type",  "application/json;charset=utf-8");//json格式上传的模式
    // httpUrlConn.setRequestProperty("Accept", "application/json"); // 接收，我们默认接收的是String

    /**
     * 通过 get 方式请求数据
     * @param url     {URL} / {URL+Param}
     * @param headers 请求的属性，也叫请求头
     * @param params  在参数过多的时候，可能你更喜欢用 Map 来进行保存你的参数
     * @return        {URL}返回的数据，类型是 String
     * @throws IOException IO异常：给定的 URL 不正确，或者其他原因，导致服务法法找到
     *         或是在向服务器上传数据时，当然还有可能在读取服务返回的数据时
     */
    public static String get(String url,
                             Map<String, String> headers,
                             Map<String, String> params) throws IOException {

        // 参数
        if (params != null) {
            url += "?" + Utils.getUrlParamsByMap(params);
        }

        HttpURLConnection httpUrlConn = httpUrlConn(url);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setRequestMethod(Constant.RequestMethodGet);

        // header
        if (headers != null) {
            header(headers, httpUrlConn);
        }

        httpUrlConn.connect();

        return readIO(httpUrlConn);
    }

    /**
     * 通过 post 方式请求数据
     * @param url     {URL}
     * @param headers 请求的属性，也叫请求头
     * @param params  用 Map 来进行保存你的参数
     * @return        {URL}返回的数据，类型是 String
     * @throws IOException IO异常：给定的 URL 不正确，或者其他原因，导致服务法法找到
     *         或是在向服务器上传数据时，当然还有可能在读取服务返回的数据时
     */
    public static String post(String url,
                              Map<String, String> headers,
                              Map<String, String> params) throws IOException {

        HttpURLConnection httpUrlConn = httpUrlConn(url);
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setRequestMethod(Constant.RequestMethodPost);
        // header
        if (headers != null) {
            header(headers, httpUrlConn);
        }

        httpUrlConn.connect();

        // out
        if (params != null) {
            PrintWriter pw = new PrintWriter(httpUrlConn.getOutputStream());
            String param = Utils.getUrlParamsByMap(params);
            pw.print(param);
            pw.flush();
            pw.close();
        }

        return readIO(httpUrlConn);
    }

    /**
     * 通过 put 方式请求数据
     *
     * <p>
     *     RESTful API风格是PUT修改数据，但在实际中，建议修改还是采用POST方式
     * </p>
     * @param url     {URL}
     * @param headers 请求的属性，也叫请求头
     * @param params  在参数过多的时候，可能你更喜欢用 Map 来进行保存你的参数
     * @return        {URL}返回的数据，类型是 String
     * @throws IOException IO异常：给定的 URL 不正确，或者其他原因，导致服务法法找到
     *         或是在向服务器上传数据时，当然还有可能在读取服务返回的数据时
     */
    public static String put(String url,
                             Map<String, String> headers,
                             Map<String, String> params) throws IOException {

        StringBuilder stringBuilder = new StringBuilder(url).append("?");
        // 参数
        if (params != null) {
            stringBuilder.append(Utils.getUrlParamsByMap(params));
        }
        // 截取 ?/&
        url = stringBuilder.substring(0, stringBuilder.length() - 1);

        HttpURLConnection httpUrlConn = httpUrlConn(url);
        httpUrlConn.setRequestMethod(Constant.RequestMethodPut);
        httpUrlConn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
        // header
        if (headers != null) {
            header(headers, httpUrlConn);
        }

        httpUrlConn.connect();

        return readIO(httpUrlConn);
    }

    /**
     * 通过 delete 方式请求数据
     * @param url     {URL}
     * @param headers 请求的属性，也叫请求头
     * @param params   在参数过多的时候，可能你更喜欢用 Map 来进行保存你的参数
     * @return {URL}返回的数据，类型是 String
     * @throws IOException IO异常：给定的 URL 不正确，或者其他原因，导致服务法法找到
     *         或是在向服务器上传数据时，当然还有可能在读取服务返回的数据时
     */
    public static String delete(String url,
                                Map<String, String> headers,
                                Map<String, String> params) throws IOException {

        StringBuilder stringBuilder = new StringBuilder(url).append("?");
        // 参数
        if (params != null) {
            stringBuilder.append(Utils.getUrlParamsByMap(params));
        }

        // 截取 ?/&
        url = stringBuilder.substring(0, stringBuilder.length() - 1);

        HttpURLConnection httpUrlConn = httpUrlConn(url);
        httpUrlConn.setRequestMethod(Constant.RequestMethodDelete);
        httpUrlConn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
        // header
        if (headers != null) {
            header(headers, httpUrlConn);
        }

        httpUrlConn.connect();

        return readIO(httpUrlConn);
    }

    // header代码段
    private static void header(Map<String, String> header, HttpURLConnection httpUrlConn) {
        if (!header.isEmpty()) {
            for (Map.Entry<String, String> map : header.entrySet()) {
                String headerKey = map.getKey();
                String headerValue = map.getValue();
                httpUrlConn.setRequestProperty(headerKey, headerValue);
            }
        }
    }

    // 属性代码段
    private static HttpURLConnection httpUrlConn(String url) throws IOException {
        URL urlObject = new URL(url);
        HttpURLConnection httpUrlConn = (HttpURLConnection) urlObject.openConnection();
        httpUrlConn.setConnectTimeout(Constant.DEFAULT_CONN_TIMEOUT);
        httpUrlConn.setReadTimeout(Constant.DEFAULT_READ_TIMEOUT);
        httpUrlConn.setUseCaches(false);
        httpUrlConn.setDoInput(true);
        return httpUrlConn;
    }

    // 读取代码段
    private static String readIO(HttpURLConnection httpUrlConn) throws IOException {
        BufferedReader bufferedReader
                = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(),
                                                           Constant.DEFAULT_CHATSET));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        // 关闭
        bufferedReader.close();
        httpUrlConn.disconnect();

        return stringBuilder.toString();
    }

    /**
     * DataOutputStream
     */
    private String doPostByUrl(String uri, String param) throws IOException {
        HttpURLConnection connection;
        URL url = new URL(uri);

        connection = (HttpURLConnection)url.openConnection();           // 新建连接实例
        connection.setConnectTimeout(Constant.DEFAULT_CONN_TIMEOUT);    // 设置连接超时时间，单位毫秒
        connection.setReadTimeout(Constant.DEFAULT_READ_TIMEOUT);       // 设置读取数据超时时间，单位毫秒
        connection.setDoInput(true);                                    // 是否打开输出流 true|false
        connection.setDoOutput(true);                                   // 是否打开输入流true|false
        connection.setRequestMethod(Constant.RequestMethodPost);        // 提交方法POST|GET
        connection.setUseCaches(false);                                 // 是否缓存true|false
        connection.connect();                                           // 打开连接端口

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(param);
        out.flush();
        out.close();

        BufferedReader reader
                = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(),
                                                Constant.DEFAULT_CHATSET));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine())!= null) {
            buffer.append(line);
        }

        // 关闭连接
        reader.close();
        connection.disconnect();

        return buffer.toString();
    }
}
