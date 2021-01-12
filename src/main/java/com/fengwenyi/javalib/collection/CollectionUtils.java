package com.fengwenyi.javalib.collection;

import java.util.Collection;

/**
 * 集合工具类
 * @author Wenyi Feng
 * @since 2018-10-15
 */
public class CollectionUtils {

    /**
     * 如果集合为{@code null}或者空，则返回{@code true}。
     * 否则，返回{@code false}
     * @param collection 待检查的集合
     * @return 集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断数组是空数组
     * @param array 待判断的数据
     * @return true：空 / false：非空
     */
    public static boolean isEmpty(String [] array) {
        return array == null || array.length == 0;
    }

    /**
     * 如果集合不为{@code null}或者空，则返回{@code true}。
     * 否则，返回{@code false}
     * @param collection 待检查的集合
     * @return 集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组不是空数组
     * @param array 待判断的数据
     * @return true：非空 / false：空
     */
    public static boolean isNotEmpty(String [] array) {
        return !isEmpty(array);
    }
}
