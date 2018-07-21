package com.fengwenyi.javalib.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

/**
 * 你可能想从{HttpServletRequest}获取一些信息
 * @author Wenyi Feng
 */
public class RequestUtil {

    // 获取客户端IP地址(不被使用，仅做参考)
    private String getRequestIpOnly(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取客户端IP地址
     * @param request 请求
     * @return ip地址
     * 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
     *
     * @throws UnknownHostException [ellipsis]
     */
    public static String getRequestIp(HttpServletRequest request) throws UnknownHostException {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inetAddress = InetAddress.getLocalHost();

                ip= inetAddress.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 如果客户端通过 request 传递数据，那么就可以使用该方法获取数据
     * 这种通常是通过 Post方式
     * @param request HttpServletRequest
     * @return 客户端上传的数据
     * @throws IOException 因为是通过IO流读取数据，
     *         因此很可能读取失败，或者NULL，导致抛出IO异常，
     */
    public static String getPostData(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //return URLDecoder.decode(sb.toString(), Constant.DEFAULT_CHATSET);
        return sb.toString();
    }
}
