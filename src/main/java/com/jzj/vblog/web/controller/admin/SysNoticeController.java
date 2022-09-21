package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysNotice;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysNoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
@RestController
@RequestMapping("/system/notice")
@SaCheckLogin
public class SysNoticeController extends BaseController {
    @Autowired
    private SysNoticeService noticeService;

    @ApiOperation("获取通知公告列表")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice) {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    @ApiOperation("根据通知公告编号获取详细信息")
    @GetMapping(value = "/{noticeId}")
    public R getInfo(@PathVariable Long noticeId) {
        return R.ok(noticeService.selectNoticeById(noticeId));
    }

    @ApiOperation("新增通知公告")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@Validated @RequestBody SysNotice notice) {
        notice.setCreateBy(getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    @ApiOperation("修改通知公告")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@Validated @RequestBody SysNotice notice) {
        notice.setUpdateBy(getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    @ApiOperation("删除通知公告")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public R remove(@PathVariable Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}

