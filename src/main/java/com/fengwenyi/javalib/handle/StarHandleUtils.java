package com.fengwenyi.javalib.handle;

import com.fengwenyi.javalib.constant.LengthConstant;
import com.fengwenyi.javalib.constant.StringConstant;
import com.fengwenyi.javalib.util.StringUtils;

/**
 * 星号（*）处理工具类
 * @author Erwin Feng
 * @since 2020/8/23 1:57 上午
 */
public class StarHandleUtils {

    public static String ip(String ip) {
        return "";
    }

    public static String phone(String phone) {
        return "";
    }

    public static String email(String email) {
        return "";
    }

    public static String idCardNo(String idCardNo) {
        return "";
    }

    public static String password() {
        return StringUtils.generateStar(LengthConstant.PASSWORD);
    }

    public static String password(int len) {
        return StringUtils.generateStar(len);
    }

    /**
     * 真实姓名星号处理
     * <p>
     *     两个字，第一个字星号处理
     * </p>
     * <p>
     *     三个字及以上，只保留第一个和最后一个字，中间都用星号处理
     * </p>
     * @param realName
     * @return
     */
    public static String realName(String realName) {
        if (StringUtils.isEmpty(realName)
                || realName.length() == 0) {
            return StringConstant.BLANK;
        }
        if (realName.length() == 2) {
            String right = StringUtils.getRight(realName, 1);
            String star = StringUtils.generateStar(1);
            return star + right;
        }
        if (realName.length() > 2) {
            String left = StringUtils.getLeft(realName, 1);
            String right = StringUtils.getRight(realName, 1);
            String star = StringUtils.generateStar(realName.length() - 2);
            return left + star + right;
        }
        return StringUtils.generateStar(1);
    }

}
