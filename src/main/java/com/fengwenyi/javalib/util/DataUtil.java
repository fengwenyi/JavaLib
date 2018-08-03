package com.fengwenyi.javalib.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Wenyi Feng(xfsy2014@gmail.com)
 * 2017-09-28 14:31
 */
public class DataUtil {

    /**
     * Post 方式请求数据
     * [该方法已移到NetDataUtil中]
     * @param uri 请求的uri
     * @param param 请求的参数
     * @return 响应的数据
     * @throws IOException io异常
     */
    @Deprecated
    public static String getDataByUri(String uri, String param) throws IOException {
        HttpURLConnection connection;
        URL url = new URL(uri);

        connection = (HttpURLConnection)url.openConnection();// 新建连接实例
        connection.setConnectTimeout(Constant.DEFAULT_CONN_TIMEOUT);// 设置连接超时时间，单位毫秒
        connection.setReadTimeout(Constant.DEFAULT_READ_TIMEOUT);// 设置读取数据超时时间，单位毫秒
        connection.setDoInput(true);// 是否打开输出流 true|false
        connection.setDoOutput(true);// 是否打开输入流true|false
        connection.setRequestMethod(Constant.RequestMethodPost);// 提交方法POST|GET
        connection.setUseCaches(false);// 是否缓存true|false
        connection.connect();// 打开连接端口

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(param);
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Constant.DEFAULT_CHATSET));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine())!= null) {
            buffer.append(line);
        }
        reader.close();
        connection.disconnect(); // 关闭连接
        return buffer.toString();
    }
}
