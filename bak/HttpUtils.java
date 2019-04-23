package com.fengwenyi.javalib.util;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fengwenyi.javalib.constant.Charset;
import com.fengwenyi.javalib.constant.ContentType;
import com.fengwenyi.javalib.constant.UserAgent;
import org.apache.http.*;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * HttpUtil:网络访问请求工具类
 *
 * <ul>
 *     <li>get请求</li>
 *     <li>post请求</li>
 * </ul>
 *
 * <p>
 *     特别说明：请求会佯装成Win7+Chrome请求，防止被攻击，如果不同意，请拒绝使用，谢谢。
 *     另外，不再像之前的版本，抛出异常，那样感觉很乱，现在都由我帮你处理，
 *     但是依然是调用{e.printStackTrace();},如果你有好的建议，可以联系我。
 * </p>
 *
 * <p>
 *     这个只是一个简单的请求工具类，是对 {org.apache.httpcomponents/httpclient} 的一个使用进行简单封装。
 *     个人觉得，网络请求本身还是很复杂的，比如，http版本，https的安全验证，cookie处理，请求转发，代理等等，
 *     这个在jdk8及以下都没有帮我们处理。
 * </p>
 * @author Wenyi Feng
 * @since 2018-11-14
 */
public class HttpUtil {

    /**
     * 请求佯装KEY
     */
    private static final String USER_AGENT_KEY = "User-Agent";

    /**
     * 请求佯装VALUE
     */
    private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36 JavaLib";

    /**
     * 通过GET方式请求数据
     * @param url URL
     * @return 响应数据（String）
     */
    public static String get(String url) {
        ExceptionUtil.notNull(url);
        return get(url, "", "");
    }

    /**
     * 通过GET方式请求数据
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param param 参数（URL编码）
     * @return 响应数据（String）
     */
    public static String get(String host, String path, String param) {
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
     */
    public static String get(String host, String path, Map<String, String> headers, String param) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try {
            // host校验，一定不能为空，否则无法进行请求
            ExceptionUtil.notNull(host);

            // 创建HttpClient
            httpClient = wrapClient(host);

            // 创建HttpGet
            HttpGet request = new HttpGet(buildUrl(host, path, param));
            defaultHeader(request);

            // header处理
            if (MapUtil.isNotEmpty(headers)) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    request.addHeader(e.getKey(), e.getValue());
                }
            }

            // 响应结果
            response = httpClient.execute(request);

            // 响应结果处理响应内容
            String result = null;

            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, Charset.UTF_8);
                }
            }

            return result;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return null;
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
     */
    public static String post(String host, String path, Map<String, String> headers, String param,
                              Map<String, String> bodies, Boolean isJson) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try {
            // host校验，一定不能为空，否则无法进行请求
            ExceptionUtil.notNull(host);

            // 创建HttpClient
            httpClient = wrapClient(host);

            // 创建HttpGet
            HttpPost request = new HttpPost(buildUrl(host, path, param));
            defaultHeader(request);

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
                    } else {
                        entity.setContentType(ContentType.FROM);
                    }
                    request.setEntity(entity);
                }
            }

            // 响应结果
            response = httpClient.execute(request);

            // 响应结果处理响应内容
            String result = null;

            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, Charset.UTF_8);
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return null;
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
     */
    public static String post(String host, String path, Map<String, String> headers, String param,
                              String body, Boolean isJson) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try {
            // host校验，一定不能为空，否则无法进行请求
            ExceptionUtil.notNull(host);

            // 创建HttpClient
            httpClient = wrapClient(host);

            // 创建HttpGet
            HttpPost request = new HttpPost(buildUrl(host, path, param));
            defaultHeader(request);

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
                } else {
                    entity.setContentType(ContentType.FROM);
                }
                request.setEntity(entity);
            }

            // 响应结果
            response = httpClient.execute(request);

            // 响应结果处理响应内容
            String result = null;

            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, Charset.UTF_8);
                }
            }

            return result;
        } catch (UnsupportedCharsetException | IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return null;
    }

    /**
     * 通过POST方式请求数据。
     * @param host 服务器主机[http(s)://ip:port]
     * @param path 服务器虚拟目录
     * @param headers 请求header设置(Map<String, String>)
     * @param param 参数（URL编码）
     * @param bodies 数据
     * @param isJson 是否采用JSON方式封装数据
     * @return 响应数据（String）
     */
    public static String post(String host, String path, Map<String, String> headers, String param,
                              byte [] bodies, Boolean isJson) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try {
            // host校验，一定不能为空，否则无法进行请求
            ExceptionUtil.notNull(host);

            // 创建HttpClient
            httpClient = wrapClient(host);

            // 创建HttpGet
            HttpPost request = new HttpPost(buildUrl(host, path, param));
            defaultHeader(request);

            // header处理
            if (MapUtil.isNotEmpty(headers)) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    request.addHeader(e.getKey(), e.getValue());
                }
            }

            // 数据
            if (bodies != null) {
                ByteArrayEntity entity = new ByteArrayEntity(bodies);
                if (isJson) {
                    entity.setContentType(ContentType.JSON);
                } else {
                    entity.setContentType(ContentType.FROM);
                }
                request.setEntity(entity);
            }

            // 响应结果
            response = httpClient.execute(request);

            // 响应结果处理响应内容
            String result = null;

            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, Charset.UTF_8);
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return null;
    }

    /**
     * 通过POST方式请求数据
     * @param url URL
     * @param body 数据
     * @return 响应数据（String）
     */
    public static String post(String url, String body) {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, body, false);
    }

    /**
     * 通过POST方式请求数据
     * @param url URL
     * @return 响应数据（String）
     */
    public static String post(String url) {
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
     */
    public static String post(String url, String body, Boolean isJson) {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, body, isJson);
    }

    /**
     * 通过POST方式请求数据
     * @param url URL
     * @param bodies 数据(Map<String, String>)
     * @return 响应数据（String）
     */
    public static String post(String url, Map<String, String> bodies) {
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
     */
    public static String post(String url, Map<String, String> bodies, Boolean isJson) {
        ExceptionUtil.notNull(url);
        return post(url, null, null, null, bodies, isJson);
    }


    //private method start----------------------------------------------------------------------------------------------

    /**
     * Build URL
     * @param host 服务器
     * @param path 虚拟站点
     * @param param 参数
     * @return 拼接后的URL
     */
    private static String buildUrl(String host, String path, String param) {
        try {
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建HttpClient
     * @param host 服务器地址
     * @return HttpClient，支持Http/Https
     */
    private static CloseableHttpClient wrapClient(String host) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        if (host.startsWith("https://")) {
            httpClient = sslClient();
        }

        return httpClient;
    }

    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL
     * 创建ConnectionManager，添加Connection配置信息
     * @return HttpClient 支持https
     */
    private static CloseableHttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override public void checkClientTrusted(X509Certificate[] xcs, String str) {}
                @Override public void checkServerTrusted(X509Certificate[] xcs, String str) {}
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            return HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 关闭连接,释放资源
     * @param httpClient CloseableHttpClient
     * @param response CloseableHttpResponse
     */
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void defaultHeader(AbstractHttpMessage request) {

        /**HTTP客户端运行的浏览器类型的详细信息。通过该头部信息，web服务器可以判断到当前HTTP请求的客户端浏览器类别*/
        request.setHeader(USER_AGENT_KEY, USER_AGENT_VALUE);

        /**指定客户端能够接收的内容类型，内容类型中的先后次序表示客户端接收的先后次序*/
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,*/*;q=0.8");

        /**指定客户端浏览器可以支持的web服务器返回内容压缩编码类型*/
        request.setHeader("Accept-Encoding", "gzip");

        /**指定HTTP客户端浏览器用来展示返回信息所优先选择的语言*/
        request.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

        /**请求表示提交内容类型或返回返回内容的MIME类型*/
        //request.setHeader("Content-Type", "application/x-www-form-urlencoded");
    }

}
