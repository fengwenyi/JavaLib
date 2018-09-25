package com.fengwenyi.javalib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志时间工具类
 * @author Wenyi Feng
 * @since 2018-09-25
 */
public class DateTimeUtil {

    /**
     * 时间字符串转时间类型
     * @param source 满足 yyyy-MM-dd HH:mm:ss 格式
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String source) throws ParseException {

        if (StringUtil.isEmpty(source))
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(source);
    }

    /**
     * Date 转 字符串
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
