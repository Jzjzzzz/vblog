package com.jzj.vblog.web.pojo.entity.job;

import lombok.Data;

import java.math.BigInteger;

/**
 * <p>
 * Job实体类
 * </p>
 *
 * @author Jzj
 * @date Created in 2022-08-08
 */
@Data
public class JobAndTrigger {
    /**
     * 定时任务名称
     */
    private String jobName;
    /**
     * 定时任务组
     */
    private String jobGroup;
    /**
     * 定时任务全类名
     */
    private String jobClassName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 重复间隔
     */
    private BigInteger repeatInterval;
    /**
     * 触发次数
     */
    private BigInteger timesTriggered;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 时区
     */
    private String timeZoneId;
    /**
     * 定时任务状态
     */
    private String triggerState;
}
