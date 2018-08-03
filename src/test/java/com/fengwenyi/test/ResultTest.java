package com.fengwenyi.test;

import com.fengwenyi.javalib.result.IReturnCode;
import com.fengwenyi.javalib.result.Result;
import com.google.gson.Gson;
import org.junit.Test;

/**
 * @author Wenyi Feng.
 */
public class ResultTest {

@Test
public void test() {
    Result result = new Result();
    // result.setResult(ReturnCode.SUCCESS);
    // result.setResult(ReturnCode.SUCCESS, "DATA");
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
}
