package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysWebInformation;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.service.SysWebInformationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站基本信息 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-28
 */
@RestController
@RequestMapping("/sys/web/information")

public class SysWebInformationController extends BaseController {
    @Autowired
    private SysWebInformationService sysWebInformationService;

    @ApiOperation("获取网站基础信息")
    @GetMapping(value = "/getInfo")
    public R getInfo() {
        return R.ok(sysWebInformationService.selectWebInformationById());
    }

    @Log(title = "网站信息", businessType = BusinessType.UPDATE)
    @ApiOperation("修改网站信息")
    @PutMapping
    @SaCheckLogin
    public R edit(@Validated @RequestBody SysWebInformation sysWebInformation) {
        return toAjax(sysWebInformationService.updateWebInformation(sysWebInformation));
    }

    @Log(title = "网站信息", businessType = BusinessType.CLEAN)
    @ApiOperation("刷新参数缓存")
    @DeleteMapping("/refreshCache")
    @SaCheckLogin
    public R refreshCache() {
        sysWebInformationService.resetInformationCache();
        return R.ok();
    }
}

