package com.fengwenyi.javalib.result;

/**
 * 返回工具类
 * @author Wenyi Feng
 * @since 2018-08-28
 */
public class ResultResponseUtil extends DefaultResult {

    /**
     * 方法使用入口
     * @return 成功
     */
    public static ResultResponseUtil ok() {
        ResultResponseUtil resultResponseUtil = new ResultResponseUtil();
        resultResponseUtil.setSuccess(true);
        return resultResponseUtil;
    }

    /**
     * 方法使用入口
     * @return 失败
     */
    public static ResultResponseUtil error() {
        ResultResponseUtil resultResponseUtil = new ResultResponseUtil();
        resultResponseUtil.setSuccess(false);
        return resultResponseUtil;
    }

    /**
     * 设置返回码
     * @param code 返回码
     * @return ResultResponseUtil
     */
    public ResultResponseUtil code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置描述信息
     * @param msg 描述
     * @return ResultResponseUtil
     */
    public ResultResponseUtil msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    /**
     * 设置返回状态（返回码，描述）
     * @param iReturnCode IReturnCode
     * @return ResultResponseUtil
     */
    public ResultResponseUtil status(IReturnCode iReturnCode) {
        this.setCode(iReturnCode.getCode());
        this.setMsg(iReturnCode.getMsg());
        return this;
    }

    /**
     * 设置返回数据
     * @param data Object
     * @return ResultResponseUtil
     */
    public ResultResponseUtil data(Object data) {
        this.setData(data);
        return this;
    }

    //------------------------------------------------------

    /**
     * 私有，我不想被你new出一个对象来进行调用
     */
    private ResultResponseUtil() {

    }
}
