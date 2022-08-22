package com.jzj.vblog.web.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.util.StrUtil;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.JobAndTrigger;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.JobVo;
import com.jzj.vblog.web.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/8/22 15:38
 * @Version 1.0
 * @Message: 定时任务Controller
 */
@RestController
@RequestMapping("/system/job")
@Slf4j
public class SysJobController extends BaseController {
    private final JobService jobService;

    @Autowired
    public SysJobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * 定时计划列表
     * @param jobAndTrigger
     * @return
     */
    @GetMapping("/list")
    @SaCheckLogin
    public TableDataInfo list(JobAndTrigger jobAndTrigger) {
        startPage();
        List<JobAndTrigger> list = jobService.selectJobList(jobAndTrigger);
        return getDataTable(list);
    }

    /**
     * 保存定时任务
     */
    @PostMapping
    public R addJob(@RequestBody JobVo form) {
        try {
            jobService.addJob(form);
        } catch (Exception e) {
            return R.error("定时任务保存失败!");
        }
        return R.ok();
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping
    public R deleteJob(JobVo form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error();
        }
        jobService.deleteJob(form);
        return R.ok();
    }

    /**
     * 暂停定时任务
     */
    @PutMapping(params = "pause")
    public R pauseJob(JobVo form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }

        jobService.pauseJob(form);
        return R.ok("暂停成功");
    }

    /**
     * 恢复定时任务
     */
    @PutMapping(params = "resume")
    public R resumeJob(JobVo form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }

        jobService.resumeJob(form);
        return R.ok("恢复成功");
    }

    /**
     * 修改定时任务，定时时间
     */
    @PutMapping(params = "cron")
    public R cronJob( JobVo form) {
        try {
            if(StringUtils.isBlank(form.getCronExpression())){
                throw new BusinessException("表达式不能为空");
            }
            jobService.cronJob(form);
        } catch (Exception e) {
            return R.error("修改定时任务出错");
        }

        return R.ok("修改成功");
    }


}
