package com.fengwenyi.javalib.util;

/**
 * 异常处理工具类
 *
 * 看博客说 java 应该提供一个异常处理的东西，不同的人可以看到不同的错误级别，我也正在向这个方向靠拢
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public class ExceptionUtil {

    /**
     * 判断对象作为参数是否为空
     * @param object 参数对象
     * @param message 异常说明信息
     * (@see org.springframework.util.Assert#isNull)
     */
    public static void isNull(Object object, String message) {
        if (object != null)
            throw new IllegalArgumentException(message); // 非法参数异常
    }

    /**
     * 判断对象作为参数是否不为空
     * @param object 参数对象
     * @param message 异常说明信息
     * (@see org.springframework.util.Assert#notNull)
     */
    public static void notNull(Object object, String message) {
        if (object == null)
            throw new IllegalArgumentException(message);
        /*
        * 如果是字符串，我不希望你的字符串是一个空字符串，那样没有任何意义，
        * 但是你仍然坚持要为空，那你可以添加一个空格(" ")试试
        * */
        if (object instanceof String)
            if (StringUtil.isEmpty((String) object))
                throw new IllegalArgumentException(message);
    }

}
