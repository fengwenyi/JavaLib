package com.fengwenyi.javalib.bean;

import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.exception.ExceptionUtils;
import com.fengwenyi.javalib.jk.IGetter;
import com.fengwenyi.javalib.jk.ISetter;
import com.fengwenyi.javalib.util.PrintUtils;
import com.fengwenyi.javalib.util.StrUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * Bean工具类
 * @author Erwin Feng
 * @since 2020/8/18
 */
public class BeanUtils {

    /* getter and setter 方法前缀 */
    private static final String [] GET_SET_PREFIX = { "get", "is", "set" };

    /**
     * 将源对象的属性值拷贝给目标对象。
     * <p>
     *     先将源对象转换成json字符，
     *     再将json字符串转换成目标对象。
     * </p>
     * @param source 源对象
     * @param targetClazz 目标对象的class
     * @param <T> 目标对象
     * @return 返回目标对象
     */
    public static <T> T copy(Object source, Class<T> targetClazz) {
        String jsonString = JsonUtils.string(source);
        return JsonUtils.object(jsonString, targetClazz);
    }


    /**
     * 获取getter方法引用为属性名
     * @param fn getter方法函数
     * @param <T> [忽略]
     * @return 返回属性名
     */
    public static <T> String getFieldNameByGet(IGetter<T, Object> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        return getFieldNameByMethodName(methodName);
    }

    /**
     * 获取setter方法引用为属性名
     * @param fn getter方法函数
     * @param <T> [忽略]
     * @param <U> [忽略]
     * @return 返回属性名
     */
    public static <T, U> String getFieldNameBySet(ISetter<T, U> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        return getFieldNameByMethodName(methodName);
    }

    // 获取类对应的Lambda
    private static SerializedLambda getSerializedLambda(Serializable fn){
        try{
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            return (SerializedLambda) method.invoke(fn);
        }
        catch (Exception e){
            PrintUtils.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    // 根据方法名获取字段名
    private static String getFieldNameByMethodName(String methodName) {
        String prefix = getGetSetMethodPrefix(methodName);
        if(StrUtils.isBlank(prefix)){
            System.err.println("无效的方法: " + methodName);
            return "";
        }
        return StrUtils.lowerCaseFirst(StrUtils.substringAfter(methodName, prefix));
    }

    // 获取 getter / setter 方法前缀
    private static String getGetSetMethodPrefix(String methodName) {
        for (String prefix : GET_SET_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return prefix;
            }
        }
        return null;
    }

}
