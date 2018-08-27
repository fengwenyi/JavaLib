package com.fengwenyi.javalib.result;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public class DefaultResultResponseBuilder implements ResultResponse.BodyBuilder {

    private final Integer code;

    public DefaultResultResponseBuilder(Integer code) {
        this.code = code;
    }


}
