package com.fengwneyi.javalib.result;

import com.fengwenyi.javalib.result.Result;
import com.fengwenyi.javalib.util.StringUtils;

/**
 * 自定义返回工具类
 * @author Erwin Feng
 * @since 1.1
 */
public class MResult<T> extends Result<T> {

    private static final long serialVersionUID = -6085866099085313725L;

    // add param success
    /* 成功的标识，ture:成功; false:失败 */
    private Boolean success = false;

    public Boolean getSuccess() {
        return success;
    }

    private MResult(Integer code, String msg, Boolean success) {
        super(code, msg);
        this.success = success;
    }

    // ok
    public static Result ok() {
        // return new Result<>(BaseCodeMsg.SUCCESS);
        // return new Result<>(200, "OK");
        return new MResult(200, "OK", true);
    }

    // fail
    public static Result fail() {
        // return new Result<>();
        return new Result<>(400, "Not Found");
    }

    // toString
    @Override
    public String toString() {
        Integer code = this.getCode();
        String msg = this.getMsg();
        T data = this.getData();

        StringBuilder sb = new StringBuilder("MResult{");

        if (code != null) // code not null
            sb.append("code=").append(code);
        if (StringUtils.isNotEmpty(msg)) // msg not empty
            sb.append(", msg='").append(msg).append('\'');
        if (success) // true
            sb.append(", success=").append(success);
        if (data != null) // data not null
            sb.append(", data=").append(data);

        sb.append("}");
        return sb.toString();
    }
    // ...
    // 请参考 {@see com.fengwenyi.javalib.result.Result}
}
