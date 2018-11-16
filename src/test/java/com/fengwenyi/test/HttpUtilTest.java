package com.fengwenyi.test;

import com.fengwenyi.javalib.constant.Charset;
import com.fengwenyi.javalib.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Wenyi Feng
 * @since 2018-11-14
 */
public class HttpUtilTest {

    @Test
    public void get1() {
        try {
            String response = HttpUtil.get("https://www.baidu.com");

            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get2() {
        String response = null;
        try {
            response = HttpUtil.get("https://www.cnblogs.com", "/codechange/p/8808922.html", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    @Test
    public void get3() {
        try {
            String response = HttpUtil.get("https://3w.huanqiu.com",
                    "/a/a4d1ef/7HP9ziM5n7q",
                    "agt=8");
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void post() {
        try {
            String rs = HttpUtil.post("https://www.panda.tv/all?pdt=1.25.psbar-menu.0.5rt56m9pmkn");
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

















}
