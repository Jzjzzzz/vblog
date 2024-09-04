package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.utils.sign.JobUtil;
import com.jzj.vblog.web.mapper.JobMapper;
import com.jzj.vblog.web.pojo.entity.job.JobAndTrigger;
import com.jzj.vblog.web.pojo.vo.JobVo;
import com.jzj.vblog.web.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * JobServiceImpl
 * </p>
 *
 * @author Jzj
 * @since 2022-08-08 11:12
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;

    private final JobMapper jobMapper;

    @Autowired
    public JobServiceImpl(Scheduler scheduler, JobMapper jobMapper) {
        this.scheduler = scheduler;
        this.jobMapper = jobMapper;
    }

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public boolean addJob(JobVo form) {
        try {
            // 启动调度器
            scheduler.start();
            // 构建Job信息
            JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass()).withIdentity(form.getJobClassName(), form.getJobGroupName()).build();
            // Cron表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());
            //根据Cron表达式构建一个Trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName(), form.getJobGroupName()).withSchedule(cron).build();
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (Exception e) {
            log.error("【定时任务】创建失败！", e);
            return false;
        }
    }

    @Override
    public boolean deleteJob(JobVo form) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
            scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
            scheduler.deleteJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
            return true;
        } catch (Exception e) {
            log.error("删除定时计划出错", e);
            return false;
        }
    }

    @Override
    public boolean pauseJob(JobVo form) {
        try {
            scheduler.pauseJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
            return true;
        } catch (Exception e) {
            log.error("暂停定时任务出错", e);
            return false;
        }
    }

    @Override
    public boolean resumeJob(JobVo form) {
        try {
            scheduler.resumeJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
            return true;
        } catch (Exception e) {
            log.error("恢复定时任务出错", e);
            return false;
        }
    }

    @Override
    public boolean cronJob(JobVo form) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 根据Cron表达式构建一个Trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (SchedulerException e) {
            log.error("重新配置定时任务出错", e);
            return false;
        }
    }


    @Override
    public List<JobAndTrigger> selectJobList(JobAndTrigger jobAndTrigger) {
        return jobMapper.list(jobAndTrigger);
    }

    @Override
    public boolean manualJob(JobVo form) {
        try {
            schedulerFactoryBean.getScheduler().triggerJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
            return true;
        } catch (SchedulerException e) {
            log.error("手动调用一次定时计划出错", e);
            return false;
        }
    }
}
