package com.fengwenyi.javalib.handle;

import com.fengwenyi.javalib.collection.CollectionUtils;
import com.fengwenyi.javalib.constant.LengthConstant;
import com.fengwenyi.javalib.constant.StringConstant;
import com.fengwenyi.javalib.util.StrUtils;

/**
 * 星号（*）处理工具类
 * @author Erwin Feng
 * @since 2020/8/23 1:57 上午
 */
public class StarHandleUtils {

    private final static int DEFAULT_PWD_LEN = 32;

    public static String ip(String ip) {
        if (StrUtils.isBlank(ip)) {
            return StringConstant.BLANK;
        }
        String[] array = ip.split("\\.");
        if (CollectionUtils.isNotEmpty(array) && array.length == 4) {
            return String.format("%s.%s.*.*", array[0], array[1]);
        }
        return StringConstant.BLANK;
    }

    /**
     * 手机号星号（*）处理
     * <p>
     *     如一个手机号是：12345678901，处理之后是：123****8901
     * </p>
     * @param phone 待处理的手机号，11位
     * @return 返回星号处理之后的手机号
     */
    public static String phone(String phone) {
        if (StrUtils.isBlank(phone)) {
            return StringConstant.BLANK;
        }
        if (phone.length() == 11) {
            String left = StrUtils.getLeft(phone, 3);
            String right = StrUtils.getRight(phone, 4);
            String star = StrUtils.generateStar(4);
            return left + star + right;
        }
        return StringConstant.BLANK;
    }

    /**
     * 邮箱星号（*）处理
     * @param email 待处理的邮箱
     * @return 返回处理之后的邮箱
     */
    public static String email(String email) {
        if (StrUtils.isBlank(email)) {
            return StringConstant.BLANK;
        }
        int index = email.lastIndexOf("@");
        if (index > 0) {
            int indexLeft = index == 1 ? 0 : 1;
            String right = StrUtils.getRight(email, email.length() - index);
            String left = StrUtils.getLeft(email, indexLeft);
            String star = StrUtils.generateStar(index - indexLeft);
            return left + star + right;
        }
        return StringConstant.BLANK;
    }

    /**
     * 身份证号码星号处理
     *
     * <p>
     *     前留3，后留4，中间由星号填充
     * </p>
     *
     * @param idCardNo 待处理的身份证号码
     * @return 返回处理之后的身份证号码
     */
    public static String idCardNo(String idCardNo) {
        return idCardNo(idCardNo, 3, 4);
    }

    /**
     * 身份证号码星号处理
     * @param idCardNo 待处理的身份证号码
     * @param leftLength 左边保留长度
     * @param rightLength 右边保留长度
     * @return 返回处理之后的身份证号码
     */
    public static String idCardNo(String idCardNo, int leftLength, int rightLength) {

        if (StrUtils.isBlank(idCardNo)) {
            return StringConstant.BLANK;
        }

        if (idCardNo.length() <= leftLength + rightLength) {
            return StringConstant.BLANK;
        }

        String leftString = StrUtils.getLeft(idCardNo, leftLength);
        String rightString = StrUtils.getRight(idCardNo, rightLength);
        int starLength = idCardNo.length() - leftLength - rightLength;
        String starString = StrUtils.generateStar(starLength);

        return leftString + starString + rightString;
    }

    /**
     * 密码星号（*）处理
     * @return 返回32位的星号密码
     */
    public static String password() {
        return StrUtils.generateStar(DEFAULT_PWD_LEN);
    }

    /**
     * 密码星号（*）处理
     * @param len 指定生成星号密码的长度
     * @return 返回指定长度的星号密码
     */
    public static String password(int len) {
        return StrUtils.generateStar(len);
    }

    /**
     * 真实姓名星号处理
     * <p>
     *     两个字，第一个字星号处理
     * </p>
     * <p>
     *     三个字及以上，只保留第一个和最后一个字，中间都用星号处理
     * </p>
     * @param realName 真实姓名
     * @return 姓名带星号
     */
    public static String realName(String realName) {
        if (StrUtils.isBlank(realName)) {
            return StringConstant.BLANK;
        }
        if (realName.length() == 2) {
            String right = StrUtils.getRight(realName, 1);
            String star = StrUtils.generateStar(1);
            return star + right;
        }
        if (realName.length() > 2) {
            String left = StrUtils.getLeft(realName, 1);
            String right = StrUtils.getRight(realName, 1);
            String star = StrUtils.generateStar(realName.length() - 2);
            return left + star + right;
        }
        return StrUtils.generateStar(1);
    }

}
