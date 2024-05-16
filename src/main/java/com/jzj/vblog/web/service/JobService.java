package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.job.JobAndTrigger;
import com.jzj.vblog.web.pojo.vo.JobVo;

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
     * @param form 表单参数
     * @return 是否成功
     */
    boolean addJob(JobVo form);

    /**
     * 删除定时任务
     *
     * @param form 表单参数
     * @return 是否成功
     */
    boolean deleteJob(JobVo form);

    /**
     * 暂停定时任务
     *
     * @param form 表单参数
     * @return 是否成功
     */
    boolean pauseJob(JobVo form);

    /**
     * 恢复定时任务
     *
     * @param form 表单参数
     * @return 是否成功
     */
    boolean resumeJob(JobVo form);

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数
     * @return 是否成功
     */
    boolean cronJob(JobVo form);


    /**
     * 查询定时任务列表
     *
     * @param jobAndTrigger 查询条件
     * @return 列表
     */
    List<JobAndTrigger> selectJobList(JobAndTrigger jobAndTrigger);

    /**
     * 手动执行一次定时计划
     *
     * @param form 参数
     * @return 是否成功
     */
    boolean manualJob(JobVo form);
}
