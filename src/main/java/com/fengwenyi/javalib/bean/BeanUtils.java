package com.fengwenyi.javalib.bean;

import com.fengwenyi.javalib.convert.JsonUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * Bean工具类
 * @author Erwin Feng
 * @since 2020/8/18
 */
public class BeanUtils {

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
        String jsonString = JsonUtils.convertString(source);
        return JsonUtils.convertObject(jsonString, targetClazz);
    }

    public interface FieldFunction<T, R> extends Function<T, R>, Serializable {

    }

    /**
     * getter方法接口定义
     */
    @FunctionalInterface
    public interface IGetter<T> extends Serializable {
        Object apply(T source);
    }
    /**
     * setter方法接口定义
     */
    @FunctionalInterface
    public interface ISetter<T, U> extends Serializable {
        void accept(T t, U u);
    }

    public static <T> String getFieldName(FieldFunction<T, ?> func) {
        try {
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
            String methodName = serializedLambda.getImplMethodName();
            String prefix = null;
            // return "获取到方法名称 = " + getter;
            if(methodName.startsWith("get")){
                prefix = "get";
            } else if (methodName.startsWith("is")){
                prefix = "is";
            }
            if(prefix == null){
                System.err.println("无效的getter方法: " + methodName);
                return "";
            }
            return lowerCaseFirst(substringAfter(methodName, prefix));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String substringAfter(String methodName, String prefix) {
        return methodName.substring(prefix.length());
    }

    private static String lowerCaseFirst(String content) {
        String first = content.substring(0, 1);
        String after = content.substring(1);
        return first.toLowerCase() + after;
    }

    /***
     * 转换setter方法引用为属性名
     * @param fn
     * @return
     */
    public static <T,R> String convertToFieldName(ISetter<T,R> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        if(!methodName.startsWith("set")){
            System.err.println("无效的setter方法: "+methodName);
        }
        // 截取set之后的字符串并转换首字母为小写（S为diboot项目的字符串工具类，可自行实现）
        return lowerCaseFirst(substringAfter(methodName, "set"));
    }

    /***
     * 获取类对应的Lambda
     * @param fn
     * @return
     */
    private static SerializedLambda getSerializedLambda(Serializable fn){
        //先检查缓存中是否已存在
        SerializedLambda lambda = null;
        try{//提取SerializedLambda并缓存
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            lambda = (SerializedLambda) method.invoke(fn);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lambda;
    }
}
