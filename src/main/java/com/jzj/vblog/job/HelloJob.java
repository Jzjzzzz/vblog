package com.jzj.vblog.job;

import com.jzj.vblog.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * <p>
 * 测试案例
 * </p>
 *
 * @author Jzj
 * @since 2022/8/22 15:36
 */
@Slf4j
public class HelloJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        log.error("Hello Job 执行时间: {}", new Date());
    }
}
