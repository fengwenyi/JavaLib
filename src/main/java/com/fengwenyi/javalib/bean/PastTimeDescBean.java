package com.fengwenyi.javalib.bean;

import lombok.Data;

/**
 * 过去的时间
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/12 12:20
 */
@Data
public class PastTimeDescBean {

    /** 时间差 */
    private Long number;

    /**
     * 单位
     * <ul>
     *  <li>年:year</li>
     *  <li>月:month</li>
     *  <li>日:day</li>
     *  <li>时:hour</li>
     *  <li>分:minute</li>
     *  <li>秒:second</li>
     *  <li>刚刚:just</li>
     * </ul>
     */
    private String unit;
}
