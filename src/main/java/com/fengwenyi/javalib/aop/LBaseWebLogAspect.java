package com.fengwenyi.javalib.aop;

import com.fengwenyi.javalib.util.RequestUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *     web请求aop切面
 * </p>
 *
 * <p>
 *     通过日志记录web请求以及方法请求处理的一些细节
 * </p>
 *
 * @author Wenyi Feng
 * @since 2018-08-30
 */
//@Slf4j
public abstract class LBaseWebLogAspect {

    /**
     * 继承类可以直接使用
     * 关于log变量的定义问题及更多细节，可以参考doc中的数码
     */
    //protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected static final Logger log = LoggerFactory.getLogger(LBaseWebLogAspect.class);

    /** 记录方法处理消耗的时间(纳秒) */
    private long startTime = 0L;

    /**
     * 需要去实现（进入方法前）
     * @param joinPoint 切点
     * @throws Exception 异常
     */
    public abstract void doBefor(JoinPoint joinPoint) throws Exception;

    /**
     * 需要实现（返回后）
     * @param ret 返回的数据
     * @throws Exception 异常
     */
    public abstract void doAfterReturn(Object ret) throws Exception;

    /**
     * 进入方法之前
     * @param joinPoint 切点
     * @param request 为了完全不侵入你的代码，所以我很难帮你拿到request，但是，你很容易，所以，我选择交给你获取
     * @throws Exception 异常
     */
    public void doBefore(JoinPoint joinPoint, HttpServletRequest request) throws Exception {

        startTime = System.nanoTime();

        // header
        //String headerStr = request.getHeader(str);

        // 请求ip
        //String ip = request.getRemoteAddr();
        String ip = RequestUtil.getRequestIp(request);

        // 请求地址（url）
        String url = request.getRequestURL().toString();

        // 请求的方式（get/post/...）
        String mode = request.getMethod();

        // 请求参数
        Map<String, String[]> paramMap = request.getParameterMap();

        // 请求类
        String className = joinPoint.getSignature().getDeclaringTypeName();

        // 请求方法（及参数类型）
        String methodName = joinPoint.getSignature().getName();

        //------------------------------------------------------------

        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        log.info("request ip:{}", ip);
        log.info("request url:{}", url);
        log.info("request mode:{}", mode);
        log.info("request classPath:{}", className + "#" + methodName);
        log.info("request paramData:{}", new Gson().toJson(paramMap));

        //------------------------------------------------------------
    }

    /**
     * 结束方法访问
     * @param ret 返回数据
     * @param request 切点
     * @throws Exception 异常
     */
    public void doAfterReturning(Object ret, HttpServletRequest request) throws Exception {

        //

        log.info("return data:{}", ret);
        long endTime = System.nanoTime();

        /** 单位：纳秒 */
        long time = endTime - startTime;
        log.info("time spead:{} (ns)", time);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }
}
