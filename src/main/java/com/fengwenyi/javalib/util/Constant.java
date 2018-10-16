package com.fengwenyi.javalib.util;

/**
 * 常量类（接口）
 * <p>
 *     我个人比较喜欢把一些常量封装在一起，
 *     当然你可以通过静态变量的方式
 * </p>
 *
 * <p>
 *     常量不能使用接口，
 *     新的使用方式，请看 {@link com.fengwenyi.javalib.constant}包
 * </p>
 *
 * @author Wenyi Feng
 */
@Deprecated
public interface Constant {

    /**
     * 编码
     */
    String DEFAULT_CHARSET = "UTF-8";

    /**
     * 连接超时时间（1分钟）
     */
    int DEFAULT_CONN_TIMEOUT = 1000 * 60;

    /**
     * 读取超时时间（10分钟）
     */
    int DEFAULT_READ_TIMEOUT = 1000 * 60 * 10;

    /**
     * get
     */
    String RequestMethodGet = "GET";

    /**
     * post
     */
    String RequestMethodPost = "POST";

    /**
     * put
     */
    String RequestMethodPut = "PUT";

    /**
     * delete
     */
    String RequestMethodDelete = "DELETE";

    /**
     * 获取ip地址的信息的uri
     */
    String IP_INFO_URI = "http://ip.taobao.com/service/getIpInfo.php";

}
