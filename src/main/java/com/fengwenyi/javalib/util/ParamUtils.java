package com.fengwenyi.javalib.util;

import net.iutil.javalib.constant.Charset;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数处理工具类
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class ParamUtils {

    /**
     * 将Map型转为请求参数型
     *
     * @param data Map类型的参数
     *
     * @return url请求的参数
     *
     * @throws UnsupportedEncodingException 异常
     */
    public static String getUrlParamsByMap(Map<String, String> data) throws UnsupportedEncodingException {
        if (data == null || data.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> i : data.entrySet()) {

            sb.append(i.getKey())
                    .append("=")
                    .append(URLEncoder.encode(i.getValue(), Charset.UTF_8))
                    .append("&");

        }
        String str = sb.toString();

        return str.substring(0, str.length() - 1);
    }

    /**
     * 将url参数转换成map
     * @param  param [ellipsis]
     * @return 参数Map
     */
    public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

}
