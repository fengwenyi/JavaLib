package com.fengwenyi.javalib.util;

import java.util.Random;

/**
 * 数学工具类
 * @author Wenyi Feng
 */
public class MathUtil {

    /**
     * 在[0, range)范围内产生一个随机数
     * @param range [ellipsis]
     * @return [0, range)范围内的随机数
     */
    public static double randomNum(int range) {
        return Math.random()*range;
    }

    /**
     * 在[y, x]范围内产生一个随机数
     * @param x 最大值
     * @param y 最小值
     * @return [y, x]范围内的随机数
     */
    public static double randomNum(int x, int y) {
        int max = x + 1;
        int min = y + 1;
        Random random = new Random();
        int result = random.nextInt(max)%(max-min+1) + min;
        return result - 1;
    }
}
