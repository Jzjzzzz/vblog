package com.jzj.vblog.web.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 定时任务详情
 * </p>
 *
 * @author Jzj
 * @date Created in 2022-08-08
 */
@Data
@Accessors(chain = true)
public class JobVo {
    /**
     * 定时任务全类名
     */
    private String jobClassName;
    /**
     * 任务组名
     */
    private String jobGroupName;
    /**
     * 定时任务cron表达式
     */
    private String cronExpression;
}
