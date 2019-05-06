package com.fengwenyi.javalib.quartz.test;

import com.fengwenyi.javalib.quartz.QuartzTask;
import com.fengwenyi.javalib.quartz.ScheduleBean;
import com.fengwenyi.javalib.quartz.TimeTypeEnum;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author Erwin Feng
 * @since 2019-04-19 23:53
 */
public class QuartzTest {

    @Test
    public void test() {
        try {
            QuartzTask quartzTask = new QuartzTask();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            String jobName = "JOB";
            String triggerName = "TRIGGER";
            ScheduleBean scheduleBean = new ScheduleBean(scheduler, HelloJob.class, jobName, triggerName);
            scheduleBean.setTimeType(TimeTypeEnum.AT_TIME);
            scheduleBean.setAtTime(System.currentTimeMillis() + 1000 * 5); // 5s之后运行
            boolean rs = quartzTask.start(scheduleBean);
            if (rs)
                System.out.println("定时任务，启动成功");
            else
                System.out.println("定时任务，启动失败");
            System.out.println("当前时间：" + new Date());
            //运行一段时间后关闭
            Thread.sleep(10000);
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
