package com.fengwenyi.javalib.quartz;

import lombok.Getter;

/**
 * 定时任务执行类型（枚举）
 * @author Wenyi Feng
 */
@Getter
public enum TimeTypeEnum {

    /** 简单的定时任务(循环)*/
    SIMPLE(1, "simpleSchedule"),

    /** 指定一个时间点执行（毫秒数[Long]） */
    AT_TIME(2, "at time"),

    /** 使用cron表达式（时间点、循环、自定义时间） */
    CRON(3, "cron expression")
    ;

    /** 代码 */
    private Integer code;

    /** 描述 */
    private String msg;

    /**
     * 通过构造方法初始化
     * @param code 代码
     * @param msg  描述
     */
    TimeTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
