package com.fengwenyi.javalib.quartz;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.quartz.Job;
import org.quartz.Scheduler;

import java.util.Map;

/**
 * ScheduleBean
 * @author Wenyi Feng
 */
@Getter
@Setter
@Accessors(chain = true)
public class ScheduleBean {

    /** quartz scheduler obj */
    private Scheduler scheduler;

    /** 编号 */
    private Long id;

    /** task name */
    private String name;

    /** task description */
    private String description;

    /** time type */
    private TimeTypeEnum timeType;

    /** 类clazz */
    private Class<? extends Job> clazz;

    /** job参数 */
    private Map<String, Object> paramJobMap;

    /** trigger参数 */
    private Map<String, Object> paramTriggerMap;

    /** cron */
    private String cron;

    /** time, simple */
    private Integer time;

    /** 时间点 */
    private Long atTime;

    /** job name */
    private String jobName;

    /** job group name */
    private String jobGroup;

    /** trigger name */
    private String triggerName;

    /** trigger group name */
    private String triggerGroup;

    /**
     * 无参数构成方法
     */
    public ScheduleBean() {
    }

    /**
     * 简单的 快速的构成方法
     * @param scheduler Scheduler对象
     * @param clazz Class
     * @param jobName 任务名称
     * @param triggerName 触发器名称
     */
    public ScheduleBean(Scheduler scheduler,
                        Class<? extends Job> clazz,
                        String jobName,
                        String triggerName) {
        this.scheduler = scheduler;
        this.clazz = clazz;
        this.jobName = jobName;
        this.triggerName = triggerName;
    }
}
