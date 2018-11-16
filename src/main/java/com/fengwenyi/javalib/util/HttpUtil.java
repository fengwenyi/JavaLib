package com.fengwenyi.javalib.util;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fengwenyi.javalib.constant.Charset;
import com.fengwenyi.javalib.constant.ContentType;
import com.fengwenyi.javalib.constant.UserAgent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 网络访问请求工具类
 * @author Wenyi Feng
 * @since 2018-11-14
 */
public class HttpUtil {


    //------------------------------------------------------------------

    /**
     * 通过GET方式请求数据
     * @param url URL
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String get(String url) throws IOException {
        ExceptionUtil.notNull(url);
        return get(url, "", "");
    }

    /**
     * 通过GET方式请求数据
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param param 参数（URL编码）
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String get(String host, String path, String param) throws IOException {
        ExceptionUtil.notNull(host);
        return get(host, path, null, param);
    }

    /**
     * 通过GET方式请求数据
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param params 参数(Map<String, String>)
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String get(String host, String path, Map<String, String> params) throws IOException {
        ExceptionUtil.notNull(host);
        return get(host, path, ParamUtil.getUrlParamsByMap(params));
    }

    /**
     * 通过GET方式请求数据
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param headers 请求header设置(Map<String, String>)
     * @param param 参数（URL编码）
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String get(String host, String path, Map<String, String> headers, String param) throws IOException {

        // host校验，一定不能为空，否则无法进行请求
        ExceptionUtil.notNull(host);

        // 创建HttpClient
        HttpClient httpClient = wrapClient(host);

        // 创建HttpGet
        HttpGet request = new HttpGet(buildUrl(host, path, param));

        // header处理
        if (MapUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }

        // 响应结果
        HttpResponse httpResponse = httpClient.execute(request);

        // 响应结果处理响应内容
        String result = null;

        if (httpResponse != null) {
            HttpEntity resEntity = httpResponse.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, Charset.UTF_8);
            }
        }

        return result;
    }

    /**
     * 通过POST方式请求数据
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param headers 请求header设置(Map<String, String>)
     * @param param 参数（URL编码）
     * @param bodies 数据(Map<String, String>)
     * @param isJson 是否采用JSON方式封装数据
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String host, String path, Map<String, String> headers, String param,
                              Map<String, String> bodies, Boolean isJson) throws IOException {

        // host校验，一定不能为空，否则无法进行请求
        ExceptionUtil.notNull(host);

        // 创建HttpClient
        HttpClient httpClient = wrapClient(host);

        // 创建HttpGet
        HttpPost request = new HttpPost(buildUrl(host, path, param));

        // header处理
        if (MapUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }

        // 数据
        if (MapUtil.isNotEmpty(bodies)) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> elem : bodies.entrySet()) {
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Charset.UTF_8);
                if (isJson) {
                    entity.setContentType(ContentType.JSON);
                    request.setHeader("User-Agent", UserAgent.CHROME_WIN_7);
                } else {
                    entity.setContentType(ContentType.FROM);
                }
                request.setEntity(entity);
            }
        }

        // 响应结果
        HttpResponse httpResponse = httpClient.execute(request);

        // 响应结果处理响应内容
        String result = null;

        if (httpResponse != null) {
            HttpEntity resEntity = httpResponse.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, Charset.UTF_8);
            }
        }

        return result;
    }


    /**
     * 通过POST方式请求数据。
     * <p>
     *     特别说明：如果采用JSON方式，则会佯装成Win7+Chrome请求，防止被攻击
     * </p>
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param headers 请求header设置(Map<String, String>)
     * @param param 参数（URL编码）
     * @param body 数据
     * @param isJson 是否采用JSON方式封装数据
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String host, String path, Map<String, String> headers, String param,
                              String body, Boolean isJson) throws IOException {

        // host校验，一定不能为空，否则无法进行请求
        ExceptionUtil.notNull(host);

        // 创建HttpClient
        HttpClient httpClient = wrapClient(host);

        // 创建HttpGet
        HttpPost request = new HttpPost(buildUrl(host, path, param));

        // header处理
        if (MapUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }

        // 数据
        if (StringUtil.isNotEmpty(body)) {
            StringEntity entity = new StringEntity(body, Charset.UTF_8);
            if (isJson) {
                entity.setContentType(ContentType.JSON);
                request.setHeader("User-Agent", UserAgent.CHROME_WIN_7);
            } else {
                entity.setContentType(ContentType.FROM);
            }
            request.setEntity(entity);
        }

        // 响应结果
        HttpResponse httpResponse = httpClient.execute(request);

        // 响应结果处理响应内容
        String result = null;

        if (httpResponse != null) {
            HttpEntity resEntity = httpResponse.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, Charset.UTF_8);
            }
        }

        return result;
    }

    /**
     * 通过POST方式请求数据。
     * <p>
     *     特别说明：如果采用JSON方式，则会佯装成Win7+Chrome请求，防止被攻击
     * </p>
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param headers 请求header设置(Map<String, String>)
     * @param param 参数（URL编码）
     * @param bodies 数据
     * @param isJson 是否采用JSON方式封装数据
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String host, String path, Map<String, String> headers, String param,
                              byte [] bodies, Boolean isJson) throws IOException {

        // host校验，一定不能为空，否则无法进行请求
        ExceptionUtil.notNull(host);

        // 创建HttpClient
        HttpClient httpClient = wrapClient(host);

        // 创建HttpGet
        HttpPost request = new HttpPost(buildUrl(host, path, param));

        // header处理
        if (MapUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }

        // 数据
        if (bodies == null) {
            ByteArrayEntity entity = new ByteArrayEntity(bodies);
            if (isJson) {
                entity.setContentType(ContentType.JSON);
                request.setHeader("User-Agent", UserAgent.CHROME_WIN_7);
            } else {
                entity.setContentType(ContentType.FROM);
            }
            request.setEntity(entity);
        }

        // 响应结果
        HttpResponse httpResponse = httpClient.execute(request);

        // 响应结果处理响应内容
        String result = null;

        if (httpResponse != null) {
            HttpEntity resEntity = httpResponse.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, Charset.UTF_8);
            }
        }

        return result;
    }

    /**
     * 通过POST方式请求数据
     * @param url URL
     * @param body 数据
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String url, String body) throws IOException {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, body, false);
    }

    /**
     * 通过POST方式请求数据
     * @param url URL
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String url) throws IOException {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, "", false);
    }

    /**
     * 通过POST方式请求数据
     * <p>
     *     特别说明：如果采用JSON方式，则会佯装成Win7+Chrome请求，防止被攻击
     * </p>
     * @param url URL
     * @param body 数据
     * @param isJson 是否采用JSON方式封装数据
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String url, String body, Boolean isJson) throws IOException {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, body, isJson);
    }

    /**
     * 通过POST方式请求数据
     * @param url URL
     * @param bodies 数据(Map<String, String>)
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String url, Map<String, String> bodies) throws IOException {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, bodies, false);
    }

    /**
     * 通过POST方式请求数据
     * <p>
     *     特别说明：如果采用JSON方式，则会佯装成Win7+Chrome请求，防止被攻击
     * </p>
     * @param url URL
     * @param bodies 数据(Map<String, String>)
     * @param isJson 是否采用JSON方式封装数据
     * @return 响应数据（String）
     * @throws IOException IO读写异常
     */
    public static String post(String url, Map<String, String> bodies, Boolean isJson) throws IOException {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, bodies, isJson);
    }


    //------------------------------------------------------------------


    /**
     * post form
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }

    /**
     * Post String
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtil.isNotEmpty(body)) {
            request.setEntity(new StringEntity(body, Charset.UTF_8));
        }

        return httpClient.execute(request);
    }

    /**
     * Post stream
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    /**
     * Put String
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtil.isNotEmpty(body)) {
            request.setEntity(new StringEntity(body, Charset.UTF_8));
        }

        return httpClient.execute(request);
    }

    /**
     * Put stream
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    /**
     * Delete
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doDelete(String host, String path, String method,
                                        Map<String, String> headers,
                                        Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }

    private static String buildUrl(String host, String path, Map<String, String> map) throws UnsupportedEncodingException {
        return buildUrl(host, path, ParamUtil.getUrlParamsByMap(map));
    }

    private static String buildUrl(String host, String path, String param) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (StringUtil.isNotEmpty(path)) {
            sbUrl.append(path);
        }
        if (StringUtil.isNotEmpty(param)) {
            StringBuilder sbParam = new StringBuilder();
            sbParam.append(URLEncoder.encode(param, Charset.UTF_8));
            if (0 < sbParam.length()) {
                sbUrl.append("?").append(sbParam);
            }
        }

        return sbUrl.toString();
    }

    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = new DefaultHttpClient();
        if (host.startsWith("https://")) {
            sslClient(httpClient);
        }

        return httpClient;
    }

    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {

                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {

                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", ssf, 443));
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*private static void ssl(HttpClient httpClient) {
        AtomicReference<SSLContext> ctx = new AtomicReference<SSLContext>(SSLContext.getInstance("TLS"));
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.get().init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx.get(), SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
    }*/

}
