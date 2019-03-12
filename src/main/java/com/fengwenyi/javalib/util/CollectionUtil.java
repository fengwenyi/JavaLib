package com.fengwenyi.javalib.util;

import java.util.Collection;

/**
 * 集合操作工具类
 * @author Wenyi Feng
 * @since 2018-10-15
 */
public class CollectionUtil {

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
     * 如果集合不为{@code null}或者空，则返回{@code true}。
     * 否则，返回{@code false}
     * @param collection 待检查的集合
     * @return 集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
