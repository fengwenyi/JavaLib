package com.fengwenyi.javalib.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

import com.fengwenyi.javalib.https.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * Https请求。
 * 虽然Java 9开始支持Https请求，但据调查目前还没有上线项目
 * 或者说不多，所以如果你的jdk版本小于9，你可以参考该类
 * @author Wenyi Feng
 */
public class HttpsClientUtil {


    /**
     * POST方式向Http(s)提交数据并获取返回结果
     *
     * @param url    URL
     * @param header Header
     * @param param  参数（Map）
     *
     * @return 获取服务器数据
     *
     * @throws KeyManagementException [ellipsis]
     * @throws NoSuchAlgorithmException [ellipsis]
     * @throws IOException [ellipsis]
     */
    public static String doPost(String url, Map<String, String> header, Map<String, String> param)
            throws KeyManagementException, NoSuchAlgorithmException, IOException {

        String result = null;
        HttpClient httpClient = new SSLClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Constant.DEFAULT_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Constant.DEFAULT_READ_TIMEOUT);
        HttpPost httpPost = new HttpPost(url);
        // 设置Header
        if (header != null) {
            Set<String> keys = header.keySet();
            for (String key : keys) httpPost.setHeader(key, header.get(key));
        }
        //设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = param.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Constant.DEFAULT_CHATSET);
            httpPost.setEntity(entity);
        }
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, Constant.DEFAULT_CHATSET);
            }
        }
        return result;
    }

    /**
     * POST方式向Http(s)提交数据并获取返回结果
     *
     * @param url URL
     * @param header Header
     * @param param 参数（String）
     *
     * @return 服务器数据
     *
     * @throws KeyManagementException [ellipsis]
     * @throws NoSuchAlgorithmException [ellipsis]
     * @throws IOException [ellipsis]
     */
    public static String doPost(String url, Map<String, String> header, String param) throws KeyManagementException,
            NoSuchAlgorithmException, IOException {

        String result = null;
        HttpClient httpClient = new SSLClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Constant.DEFAULT_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Constant.DEFAULT_READ_TIMEOUT);
        HttpPost httpPost = new HttpPost(url);
        Iterator iterator;
        if (header != null) {
            Set<String> keys = header.keySet();
            iterator = keys.iterator();

            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                httpPost.setHeader(key, header.get(key));
            }
        }

        httpPost.setEntity(new StringEntity(param, Constant.DEFAULT_CHATSET));

        HttpResponse response = httpClient.execute(httpPost);

        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, Constant.DEFAULT_CHATSET);
            }
        }

        return result;
    }

}
