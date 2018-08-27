package com.fengwenyi.javalib.result;

import com.fengwenyi.javalib.util.ExceptionUtil;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-08-27
 */
public interface ResultResponse<T> {

    Result<T> build(T data);

    Result<T> build();

    interface BodyBuilder {

    }

    interface DataBuild<B extends BodyBuilder> {



    }

    static BodyBuilder ok() {
        return status(0);
    }

    static BodyBuilder status(Integer code) {
        ExceptionUtil.notNull(code, "Status code must not be null");
        return new DefaultResultResponseBuilder(code);
    }

    static Result<B> build(T data) {

    }

}
