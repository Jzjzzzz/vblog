package com.jzj.vblog.web.mapper;

import com.jzj.vblog.web.pojo.entity.job.JobAndTrigger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * JobMapper
 * </p>
 *
 * @author Jzj
 * @since 2022-08-08 11:12
 */
@Component
public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list(JobAndTrigger jobAndTrigger);
}
