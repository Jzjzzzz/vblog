package com.jzj.vblog.web.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.util.StrUtil;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.JobAndTrigger;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.JobVo;
import com.jzj.vblog.web.service.JobService;
import io.swagger.annotations.ApiOperation;
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
@SaCheckLogin
public class SysJobController extends BaseController {
    private final JobService jobService;

    @Autowired
    public SysJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @ApiOperation("定时计划列表")
    @GetMapping
    @SaCheckLogin
    public TableDataInfo list(JobAndTrigger jobAndTrigger) {
        startPage();
        List<JobAndTrigger> list = jobService.selectJobList(jobAndTrigger);
        return getDataTable(list);
    }

    @ApiOperation("保存定时任务")
    @PostMapping
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    public R addJob(@RequestBody JobVo form) {
        try {
            jobService.addJob(form);
        } catch (Exception e) {
            return R.error("定时任务保存失败!");
        }
        return R.ok();
    }

    @ApiOperation("删除定时任务")
    @DeleteMapping
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    public R deleteJob(@RequestBody JobVo form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error();
        }
        jobService.deleteJob(form);
        return R.ok();
    }

    @ApiOperation("暂停定时任务")
    @PutMapping("/pause")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    public R pauseJob(@RequestBody JobVo form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }
        jobService.pauseJob(form);
        return R.ok("暂停成功");
    }

    @ApiOperation("恢复定时任务")
    @PutMapping("/resume")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    public R resumeJob(@RequestBody JobVo form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }

        jobService.resumeJob(form);
        return R.ok("恢复成功");
    }

    @ApiOperation("修改定时任务，定时时间")
    @PutMapping("/update")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    public R cronJob(@RequestBody JobVo form) {
        try {
            if (StringUtils.isBlank(form.getCronExpression())) {
                throw new BusinessException("表达式不能为空");
            }
            jobService.cronJob(form);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
        return R.ok("修改成功");
    }

    @ApiOperation("手动调用一次定时计划")
    @PostMapping("/manualJob")
    @Log(title = "定时任务", businessType = BusinessType.OTHER)
    public R manualJob(@RequestBody JobVo form){
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }
        jobService.manualJob(form);
        return R.ok("调用成功");
    }

}
