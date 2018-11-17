package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.constant.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class IpUtil {

    /**
     * 通过ip获取位置
     * @param ip ip地址
     * @return （国家）（地区）（省份）（城市）（县级）（运营商）
     * @throws IOException io异常
     */
    public static String getPosition(String ip) throws IOException {
        JsonObject dataObject = getIpInfo(ip);
        String country = dataObject.get("country").getAsString();
        String area = dataObject.get("area").getAsString();
        String region = dataObject.get("region").getAsString();
        String city = dataObject.get("city").getAsString();
        String county = dataObject.get("county").getAsString();
        String isp = dataObject.get("isp").getAsString();
        return country + area + region + city + county + isp;
    }

    /**
     * 获取ip位置信息
     * @param ip ip地址
     * @return json数据
     * @throws IOException io异常
     */
    public static JsonObject getIpInfo(String ip) throws IOException {
        String param = "?ip=" + ip;
        String ipInfoStr = HttpUtil.get(URL.IP_INFO_URI + param);
        System.out.println(ipInfoStr);
        JsonObject ipInfoJsonObject = new JsonParser().parse(ipInfoStr).getAsJsonObject();
        return ipInfoJsonObject.get("data").getAsJsonObject();
    }

}
