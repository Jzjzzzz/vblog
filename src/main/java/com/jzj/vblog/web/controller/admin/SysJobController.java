package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.job.JobAndTrigger;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.JobVo;
import com.jzj.vblog.web.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 定时任务 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/8/22 15:38
 */
@Api(tags = "定时任务管理")
@RestController
@RequestMapping("/system/job")
@Slf4j
public class SysJobController extends BaseController {

    private final JobService jobService;

    @Autowired
    public SysJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @ApiOperation("定时计划列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.job.list')")
    public TableDataInfo list(JobAndTrigger jobAndTrigger) {
        startPage();
        List<JobAndTrigger> list = jobService.selectJobList(jobAndTrigger);
        return getDataTable(list);
    }

    @ApiOperation("保存定时任务")
    @PostMapping
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('btn.job.add')")
    public R addJob(@RequestBody JobVo form) {
        return toAjax(jobService.addJob(form));
    }

    @ApiOperation("删除定时任务")
    @DeleteMapping
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('btn.job.del')")
    public R deleteJob(@RequestBody JobVo form) {
        if (StringUtils.isAllBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error();
        }
        return toAjax(jobService.deleteJob(form));
    }

    @ApiOperation("暂停定时任务")
    @PutMapping("/pause")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('btn.job.edit')")
    public R pauseJob(@RequestBody JobVo form) {
        if (StringUtils.isAllBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }

        return toAjax(jobService.pauseJob(form));
    }

    @ApiOperation("恢复定时任务")
    @PutMapping("/resume")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('btn.job.edit')")
    public R resumeJob(@RequestBody JobVo form) {
        if (StringUtils.isAllBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }
        return toAjax(jobService.resumeJob(form));
    }

    @ApiOperation("修改定时任务，定时时间")
    @PutMapping("/update")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('btn.job.edit')")
    public R cronJob(@RequestBody JobVo form) {
        if (StringUtils.isBlank(form.getCronExpression())) {
            return R.error("表达式不能为空");
        }
        return toAjax(jobService.cronJob(form));
    }

    @ApiOperation("手动调用一次定时计划")
    @PostMapping("/manualJob")
    @Log(title = "定时任务", businessType = BusinessType.OTHER)
    @PreAuthorize("hasAuthority('btn.job.edit')")
    public R manualJob(@RequestBody JobVo form) {
        if (StringUtils.isAllBlank(form.getJobGroupName(), form.getJobClassName())) {
            return R.error("参数不能为空");
        }
        return toAjax(jobService.manualJob(form));
    }

}
