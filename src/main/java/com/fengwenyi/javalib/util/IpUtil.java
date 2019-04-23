package com.fengwenyi.javalib.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengwenyi.javalib.constant.URL;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class IpUtil {

    /**
     * 请求佯装KEY
     */
    private static final String USER_AGENT_KEY = "User-Agent";

    /**
     * 请求佯装VALUE
     */
    private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36 JavaLib";


    /**
     * 通过ip获取位置
     * @param ip ip地址
     * @return （国家）（地区）（省份）（城市）（县级）（运营商）
     * @throws IOException io异常
     */
    public static String getPosition(String ip) throws IOException {
        JSONObject jsonObject = getIpInfo(ip);
        if (jsonObject != null) {
            String country = jsonObject.get("country").toString();
            String area = jsonObject.get("area").toString();
            String region = jsonObject.get("region").toString();
            String city = jsonObject.get("city").toString();
            String county = jsonObject.get("county").toString();
            String isp = jsonObject.get("isp").toString();
            return country + area + region + city + county + isp;
        }
        return null;
    }

    /**
     * 获取ip位置信息
     * @param ip ip地址
     * @return json数据
     * @throws IOException io异常
     */
    public static JSONObject getIpInfo(String ip) throws IOException {
        String param = "?ip=" + ip;
        String ipInfoStr = getIpInfoByIp(URL.IP_INFO_URI + param);
        JSONObject jsonObject = JSON.parseObject(ipInfoStr);
        Integer code = (Integer) jsonObject.get("code");
        if (code == 0) {
            return jsonObject.getJSONObject("data");
        }
        return null;
    }

    /**
     * 通过IP获取IP信息
     * @param url url
     * @return 服务器响应字符串
     * @throws IOException IO异常
     */
    private static String getIpInfoByIp(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .removeHeader(USER_AGENT_KEY)
                .addHeader(USER_AGENT_KEY, USER_AGENT_VALUE)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body() == null ? "" : response.body().string();
    }

}
