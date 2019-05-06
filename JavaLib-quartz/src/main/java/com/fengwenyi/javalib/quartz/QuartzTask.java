package com.fengwenyi.javalib.quartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Quartz Base Task
 *
 * <ul>
 *     <li>开启定时任务</li>
 *     <li>暂停定时任务</li>
 *     <li>恢复定时任务</li>
 *     <li>停止定时任务</li>
 * </ul>
 *
 * <p>
 *     暂停定时任务：暂停所有定时任务，根据job暂停定时任务，根据trigger暂停定时任务
 * </p>
 *
 * <p>
 *     停止定时任务后，不能再启动
 * </p>
 * @author Wenyi Feng
 */
public class QuartzTask {

    /** Scheduler对象 */
    private static Scheduler scheduler;

    /**
     * 开启一个定时任务（如果定时任务正在运行，则开启失败）
     * @param scheduleBean schedule bean
     * @return 定时任务开启是否成功：true，开启成功；false:开启失败
     * @throws SchedulerException 异常
     */
    public boolean start(ScheduleBean scheduleBean)
            throws SchedulerException {

        // 如果定时器以及启动，那么将无法再次启动
        if (!status()) {

            // 如果不给参数，我们将无法开启定时任务，因为Scheduler对象将变得不可控
            if (scheduleBean == null)
                return false;

            // jobDetail
            JobDetail jobDetail = getJobDetail(scheduleBean);

            // time type
            TimeTypeEnum timeTypeEnum = scheduleBean.getTimeType();

            // trigger
            Trigger trigger = null;
            if (timeTypeEnum == null) {

                // TimeTypeEnum.SIMPLE.getCode()
                switch (timeTypeEnum.getCode()) {
                    case 1: // simple
                        trigger = getTriggerBySimple(scheduleBean);
                        break;
                    case 2: // atTime
                        trigger = getTriggerByAtTime(scheduleBean);
                        break;
                    case 3: // cron
                        trigger = getTriggerByCron(scheduleBean);
                        break;
                    default:
                        trigger = getTrigger(scheduleBean);
                        break;
                }

            } else {
                trigger = getTrigger(scheduleBean);
            }

            if (scheduler == null)
                scheduler = scheduleBean.getScheduler();

            if (jobDetail != null && trigger != null) {
                // 参数
                paramJob(jobDetail, scheduleBean.getParamJobMap());
                paramTrigger(trigger, scheduleBean.getParamTriggerMap());

                scheduler.scheduleJob(jobDetail, trigger);
            }
            scheduler.start();
            return true;
        }
        return false;
    }

    /**
     * 查询定时任务状态
     * @return 定时任务当前状态：true，运行中；false:已停止
     * @throws SchedulerException 异常
     */
    public boolean status() throws SchedulerException {
        if (scheduler != null) {
            return scheduler.isStarted();
        }
        return false;
    }

    /**
     * 停止定时任务
     * @return 定时任务是否成功关闭：true，已关闭；false:关闭失败
     *          可通过调用{#link status()}方法查看定时任务当前状态
     * @throws SchedulerException 异常
     */
    public boolean stop() throws SchedulerException {
        if (scheduler != null) {
            scheduler.shutdown();
            return true;
        }
        return false;
    }

    /**
     * 暂停所有定时任务
     * @return true：成功；false：失败
     * @throws SchedulerException 异常
     */
    public boolean pauseAll() throws SchedulerException {
        if (scheduler != null) {
            scheduler.pauseAll();
            return true;
        }
        return false;
    }

    /**
     * 根据JobKey暂停定时任务
     * @param jobKey {@link JobKey}
     * @return true：成功；false：失败
     * @throws SchedulerException 异常
     */
    public boolean pauseByJobKey(JobKey jobKey) throws SchedulerException {
        if (scheduler != null) {
            scheduler.pauseJob(jobKey);
            return true;
        }
        return false;
    }

    /**
     * 根据JobKeys暂停定时任务
     * @param jobKeys {@link GroupMatcher}
     * @return true：成功；false：失败
     * @throws SchedulerException 异常
     */
    public boolean pauseByJobKeys(GroupMatcher<JobKey> jobKeys) throws SchedulerException {
        if (scheduler != null) {
            scheduler.pauseJobs(jobKeys);
            return true;
        }
        return false;
    }

    /**
     * 根据TriggerKey暂停定时任务
     * @param triggerKey {@link TriggerKey}
     * @return true：成功；false：失败
     * @throws SchedulerException 异常
     */
    public boolean pauseByTriggerKey(TriggerKey triggerKey) throws SchedulerException {
        if (scheduler != null) {
            scheduler.pauseTrigger(triggerKey);
            return true;
        }
        return false;
    }

    /**
     * 根据TriggerKeys暂停定时任务
     * @param triggerKeys {@link GroupMatcher}
     * @return true：成功；false：失败
     * @throws SchedulerException 异常
     */
    public boolean pauseByTriggerKeys(GroupMatcher<TriggerKey> triggerKeys) throws SchedulerException {
        if (scheduler != null) {
            scheduler.pauseTriggers(triggerKeys);
            return true;
        }
        return false;
    }

    //-------------------------------------------------------private method start

    /**
     * 获取 JobDetail 对象
     * @param scheduleBean {@link ScheduleBean}
     * @return {@link JobDetail}
     */
    private JobDetail getJobDetail(ScheduleBean scheduleBean) {
        return newJob(scheduleBean.getClazz())
                .withIdentity(scheduleBean.getJobName(),
                        scheduleBean.getJobGroup())
                .build();
    }

    /**
     * 将参数封装到JobDetail对象中
     * @param obj jobDetail
     * @param paramMap
     */
    private void paramJob(JobDetail obj, Map<String, Object> paramMap) {
        if (paramMap != null && !paramMap.isEmpty()) {
            Set<String> keys = paramMap.keySet();
            for (String key : keys) {
                obj.getJobDataMap().put(key, paramMap.get(key));
            }
        }
    }

    /**
     * 将参数封装到Trigger对象中
     * @param obj trigger
     * @param paramMap
     */
    private void paramTrigger(Trigger obj, Map<String, Object> paramMap) {
        if (paramMap != null && !paramMap.isEmpty()) {
            Set<String> keys = paramMap.keySet();
            for (String key : keys) {
                obj.getJobDataMap().put(key, paramMap.get(key));
            }
        }
    }

    /**
     * 获取trigger(simpleSchedule)
     * @param scheduleBean
     * @return
     */
    private Trigger getTriggerSimple(ScheduleBean scheduleBean) {
        return newTrigger()
                .withIdentity(scheduleBean.getTriggerName(), scheduleBean.getTriggerGroup())
                .startNow()

                .withSchedule(simpleSchedule() // simple
                        .withIntervalInSeconds(scheduleBean.getTime()) // 秒钟
                        .repeatForever() // 重复
                )
                .build();
    }

    /**
     * 获取trigger(atTime)
     * @param scheduleBean
     * @return
     */
    private Trigger getTriggerAtTime(ScheduleBean scheduleBean) {
        return newTrigger()
                .withIdentity(scheduleBean.getTriggerName(),
                        scheduleBean.getTriggerGroup())
                .startAt(new Date(scheduleBean.getAtTime()))
                .build();
    }

    /**
     * 获取trigger(cron 表达式)
     * @param scheduleBean
     * @return
     */
    private Trigger getTriggerCron(ScheduleBean scheduleBean) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleBean.getCron());
        return newTrigger()
                .withIdentity(scheduleBean.getTriggerName(),
                        scheduleBean.getTriggerGroup())
                .withSchedule(scheduleBuilder)
                .build();
    }

    /**
     * 通过策略获取Trigger
     *
     * 策略：
     *  如果你没有指定，则看你传的参数
     *      cron->atTime->simple
     *      检查顺序如上，后者会覆盖前者
     *      简言之, 执行顺序：simple > atTime > cron
     *
     *  如果你指定了类型，那么就按你指定的类型进行封装
     *      如果你指定的类型没有值，依旧采用上面的策略进行处理
     * @param scheduleBean
     * @return
     */
    private Trigger getTrigger(ScheduleBean scheduleBean) {
        Trigger trigger = null;

        // cron
        String cron = scheduleBean.getCron();
        if (null != cron
                && !"".equals(cron.trim())
                && cron.length() > 0)
            trigger = getTriggerCron(scheduleBean);

        // at time
        Long atTime = scheduleBean.getAtTime();
        if (null != atTime && atTime > 0)
            trigger = getTriggerAtTime(scheduleBean);

        // simple
        Integer time = scheduleBean.getTime();
        if (null != time && time > 0)
            trigger = getTriggerSimple(scheduleBean);

        // 下而上覆盖

        return trigger;
    }

    /**
     * 通过策略获取Trigger
     * @param scheduleBean
     * @return
     */
    private Trigger getTriggerBySimple(ScheduleBean scheduleBean) {
        return getTrigger(scheduleBean);
    }

    /**
     * 通过策略获取Trigger
     * @param scheduleBean
     * @return
     */
    private Trigger getTriggerByAtTime(ScheduleBean scheduleBean) {
        Trigger trigger = null;

        // cron
        String cron = scheduleBean.getCron();
        if (null != cron
                && !"".equals(cron.trim())
                && cron.length() > 0)
            trigger = getTriggerCron(scheduleBean);

        // simple
        Integer time = scheduleBean.getTime();
        if (null != time && time > 0)
            trigger = getTriggerSimple(scheduleBean);

        // at time
        Long atTime = scheduleBean.getAtTime();
        if (null != atTime && atTime > 0)
            trigger = getTriggerAtTime(scheduleBean);

        // 下而上覆盖

        return trigger;
    }

    /**
     * 通过策略获取Trigger
     * @param scheduleBean
     * @return
     */
    private Trigger getTriggerByCron(ScheduleBean scheduleBean) {
        Trigger trigger = null;

        // at time
        Long atTime = scheduleBean.getAtTime();
        if (null != atTime && atTime > 0)
            trigger = getTriggerAtTime(scheduleBean);

        // simple
        Integer time = scheduleBean.getTime();
        if (null != time && time > 0)
            trigger = getTriggerSimple(scheduleBean);

        // cron
        String cron = scheduleBean.getCron();
        if (null != cron
                && !"".equals(cron.trim())
                && cron.length() > 0)
            trigger = getTriggerCron(scheduleBean);

        // 下而上覆盖

        return trigger;
    }

    //-------------------------------------------------------private method end
}
