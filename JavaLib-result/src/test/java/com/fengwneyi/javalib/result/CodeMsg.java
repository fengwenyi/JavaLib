package com.fengwneyi.javalib.result;

import com.fengwenyi.javalib.result.BaseCodeMsg;

/**
 * 返回码
 * @author Erwin Feng
 * @since 2019-03-31 17:00
 */
public class CodeMsg {

    /*------------------------common error---------------------------------*/
    /* 500 */
    public static final BaseCodeMsg ERROR_COMMON_INTERNAL_SERVICE_ERROR = BaseCodeMsg.app(500100, "内部服务错误");

    /*------------------------user error---------------------------------*/

    /* user account not found */
    public static final BaseCodeMsg ERROR_USER_ACCOUNT_NOT_FOUND = BaseCodeMsg.app(10001, "该账号未注册");

    /* user password incorrect */
    public static final BaseCodeMsg ERROR_USER_PASSWORD_INCORRECT = BaseCodeMsg.app(10002, "密码不正确");

    /* user locked */
    public static final BaseCodeMsg ERROR_USER_LOCKED = BaseCodeMsg.app(10003, "该用户已被锁定");

    /*------------------------param error---------------------------------*/
    /* param must not null */
    public static final BaseCodeMsg ERROR_PARAM_MUST_NOT_NULL = BaseCodeMsg.app(20001, "参数不能为空");

    /* param illegal */
    public static final BaseCodeMsg ERROR_PARAM_ILLEGAL = BaseCodeMsg.app(20002, "非法参数");

}
