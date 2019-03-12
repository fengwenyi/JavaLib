package com.fengwenyi.javalib.result;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author Wenyi Feng
 * @since 2019-03-12
 */
public class TestResult {

    @Test
    public void json() {
        Result<Object> result = Result.success();
        System.out.println(JSON.toJSONString(result));
    }

}
