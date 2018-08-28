package com.fengwenyi.test;

import com.fengwenyi.javalib.handler.Handler;
import com.fengwenyi.javalib.result.IReturnCode;
import com.fengwenyi.javalib.result.Result;
import com.fengwenyi.javalib.result.ResultResponse;
import com.fengwenyi.javalib.result.ResultResponseUtil;
import com.google.gson.Gson;
import org.junit.Test;

/**
 * @author Wenyi Feng.
 */
public class ResultTest {

    @Test
    public void test() {
        Result result = new Result();
        // result.setResult(DefaultReturnCode.SUCCESS);
        // result.setResult(DefaultReturnCode.SUCCESS, "DATA");
        result.setResult(ReturnCode.ERROR_500);
        System.out.print(new Gson().toJson(result));
    }

    enum ReturnCode implements IReturnCode {
        // 500
        ERROR_500(500, "(Error)程序出错"),

        SUCCESS(0, "Success")
        ;

        private Integer code;
        private String msg;

        ReturnCode(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Test
    public void test2() {
        //ResultResponse.ok().
    }

    @Test
    public void test3() {
        // ok -> success
        /*ResultResponse.ok().build();
        ResultResponse.ok().build(data);
        ResultResponse.ok().code().msg().data().build();
        ResultResponse.ok().code().msg().build();
        ResultResponse.ok().status().data().build();
        ResultResponse.ok().status().build();

        //
        ResultResponse.error().code().msg().build();
        ResultResponse.error().code().msg().data.build();
        ResultResponse.error().status().build();
        ResultResponse.error().status().data().build();*/

    }

    public Result<Long> test4() {
        //return new ResultResponse.Builder<Long>().ok().code(0).msg("1234").builder();
        //return new ResultResponse.Builder<>().ok().builder();
//        return new ResultResponse.Builder().ok().builder();
        return new ResultResponse.Builder<Long>().ok().builder(12345L);
        //return new ResultResponse.Builder<>().ok().code(110).msg("123").builder();
    }

    @Test
    public void test5() {
        Result result = test4();
        System.out.println(new Gson().toJson(result));
    }

    @Test
    public void testUtil() {
        ResultResponseUtil.ok().msg("").data(123L);
    }
}
