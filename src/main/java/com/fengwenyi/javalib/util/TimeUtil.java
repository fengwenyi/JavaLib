package com.fengwenyi.javalib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time
 * @author Wenyi Feng
 */
public class TimeUtil {

    // 由过去的某一时间,计算距离当前的时间
    private String CalculateTime(long time){
        long nowTime=System.currentTimeMillis();  //获取当前时间的毫秒数
        String msg = null;

        long dateDiff=nowTime-time;

        if(dateDiff<0){
            msg= dateDiff + "ms之后";
        }else{
            long dateTemp1=dateDiff / 1000; // 秒
            long dateTemp2=dateTemp1 / 60;  // 分钟
            long dateTemp3=dateTemp2 / 60;  // 小时
            long dateTemp4=dateTemp3 / 24;  // 天数
            long dateTemp5=dateTemp4 / 30;  // 月数
            long dateTemp6=dateTemp5 / 12;  // 年数

            if(dateTemp6>0){
                msg = dateTemp6+"年前";

            }else if(dateTemp5>0){
                msg = dateTemp5+"个月前";

            }else if(dateTemp4>0){
                if (dateTemp4 > 7) {
                    long week = dateTemp4 / 7;
                    msg = week+"周前";
                } else {
                    msg = dateTemp4+"天前";
                }

            }else if(dateTemp3>0){
                msg = dateTemp3+"小时前";

            }else if(dateTemp2>0){
                msg = dateTemp2+"分钟前";

            }else if(dateTemp1>0){
                msg = "刚刚";

            }
        }
        return msg;

    }

    /**
     * 距离现在多久，语义化(yyyy-MM-dd HH:mm:ss)
     * @param time [ellipsis]
     * @return [ellipsis]
     * @throws ParseException [ellipsis]
     */
    public String longTime(String time) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//指定时间格式
        Date setTime = sdf.parse(time);  //将字符串转换为指定的时间格式
        long reset=setTime.getTime();   //获取指定时间的毫秒数
        return CalculateTime(reset);
    }

    /**
     * 距离现在多久，语义化(毫秒数)
     * @param time [ellipsis]
     * @return [ellipsis]
     */
    public String longTime(long time) {
        return CalculateTime(time);
    }

}
