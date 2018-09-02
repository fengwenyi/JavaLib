package com.fengwenyi.test;

import com.fengwenyi.javalib.result.DefaultReturnCode;
import com.fengwenyi.javalib.result.IReturnCode;
import com.fengwenyi.javalib.result.Result;
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

    public Result test4() {
        //return new ResultResponse.Builder<Long>().ok().code(0).msg("1234").builder();
        //return new ResultResponse.Builder<>().ok().builder();
//        return new ResultResponse.Builder().ok().builder();
        //return new ResultResponse.Builder<Long>().ok().builder(12345L);
        //return new ResultResponse.Builder<>().ok().code(110).msg("123").builder();
        return null;
    }

    @Test
    public void test5() {
        Result result = test4();
        System.out.println(new Gson().toJson(result));
    }

    @Test
    public void testUtil() {

        ResultResponseUtil.ok().data("");

        ResultResponseUtil.ok();

        ResultResponseUtil.ok().code(0).msg("Success").data("data");

        Result result = ResultResponseUtil.ok().code(0).msg("Success");
        System.out.println(result);
        ResultResponseUtil.ok().code(0).msg("Success").data("data");
        System.out.println();
        System.out.println(ResultResponseUtil.ok().status(DefaultReturnCode.SUCCESS));
        System.out.println(ResultResponseUtil.ok().status(DefaultReturnCode.SUCCESS).data("data"));

        System.out.println(ResultResponseUtil.error().code(0).msg("Success"));
        System.out.println(ResultResponseUtil.error().code(0).msg("Success").data("data"));
        System.out.println(ResultResponseUtil.error().status(DefaultReturnCode.SUCCESS));
        System.out.println(new Gson().toJson(ResultResponseUtil.error().status(DefaultReturnCode.SUCCESS).data("data")));
    }

    @Test
    public void test6() {
        Result result1 = ResultResponseUtil.ok().code(0).msg("Success");
        Result result2 = ResultResponseUtil.ok().code(0).msg("Success").data("data");
        Result result3 = ResultResponseUtil.ok().status(DefaultReturnCode.SUCCESS);
        Result result4 = ResultResponseUtil.ok().status(DefaultReturnCode.SUCCESS).data("data");

        Result result5 = ResultResponseUtil.error().code(-1).msg("Error");
        Result result6 = ResultResponseUtil.error().code(-1).msg("Error").data("data");
        Result result7 = ResultResponseUtil.error().status(DefaultReturnCode.ERROR_INIT);
        Result result8 = ResultResponseUtil.error().status(DefaultReturnCode.ERROR_INIT).data("data");

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
        System.out.println(result7);
        System.out.println(result8);

        System.out.println(new Gson().toJson(result1));
        System.out.println(new Gson().toJson(result2));
        System.out.println(new Gson().toJson(result3));
        System.out.println(new Gson().toJson(result4));
        System.out.println(new Gson().toJson(result5));
        System.out.println(new Gson().toJson(result6));
        System.out.println(new Gson().toJson(result7));
        System.out.println(new Gson().toJson(result8));
    }
}
