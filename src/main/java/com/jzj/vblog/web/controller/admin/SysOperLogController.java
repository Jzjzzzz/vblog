package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysOperLog;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysOperLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22 11:12
 */
@Api(tags = "操作日志管理")
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

    @Autowired
    private SysOperLogService operLogService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.operlog.list')")
    public TableDataInfo list(SysOperLog operLog) {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{operIds}")
    @PreAuthorize("hasAuthority('btn.operlog.del')")
    public R remove(@PathVariable Long[] operIds) {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    @PreAuthorize("hasAuthority('btn.operlog.del')")
    public R clean() {
        operLogService.cleanOperLog();
        return R.ok();
    }
}

