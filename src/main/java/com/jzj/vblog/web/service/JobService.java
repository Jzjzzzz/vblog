package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.job.JobAndTrigger;
import com.jzj.vblog.web.pojo.vo.JobVo;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @author Jzj
 * @since 2022-08-08
 */
public interface JobService {
    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobVo}
     * @throws Exception 异常
     */
    void addJob(JobVo form) throws Exception;

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobVo}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobVo form) throws SchedulerException;

    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobVo}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobVo form) throws SchedulerException;

    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobVo}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobVo form) throws SchedulerException;

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobVo}
     * @throws Exception 异常
     */
    void cronJob(JobVo form) throws Exception;


    List<JobAndTrigger> selectJobList(JobAndTrigger jobAndTrigger);

    /**
     * 手动执行一次定时计划
     * @param form
     */
    void manualJob(JobVo form);
}
