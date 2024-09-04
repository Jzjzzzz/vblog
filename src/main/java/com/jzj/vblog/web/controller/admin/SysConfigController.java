package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysConfig;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Api(tags = "参数配置表管理")
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService configService;

    @ApiOperation("获取参数配置列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.config.list')")
    public TableDataInfo list(SysConfig config) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @ApiOperation("根据参数编号获取详细信息")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable Long id) {
        return R.ok(configService.selectConfigById(id));
    }

    @ApiOperation("根据参数键名查询参数值")
    @GetMapping(value = "/configKey/{configKey}")
    public R getConfigKey(@PathVariable String configKey) {
        return R.ok(configService.selectConfigByKey(configKey));
    }

    @Log(title = "参数配置", businessType = BusinessType.INSERT)
    @ApiOperation("新增参数配置")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.config.add')")
    public R add(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return R.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(getUsername());
        return toAjax(configService.insertConfig(config));
    }

    @Log(title = "参数配置", businessType = BusinessType.UPDATE)
    @ApiOperation("修改参数配置")
    @PutMapping
    @PreAuthorize("hasAuthority('btn.config.edit')")
    public R edit(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return R.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(getUsername());
        return toAjax(configService.updateConfig(config));
    }

    @Log(title = "参数配置", businessType = BusinessType.DELETE)
    @ApiOperation("删除参数配置")
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.config.del')")
    public R remove(@PathVariable Long[] ids) {
        configService.deleteConfigByIds(ids);
        return success();
    }

    @Log(title = "参数配置", businessType = BusinessType.CLEAN)
    @ApiOperation("刷新参数缓存")
    @DeleteMapping("/refreshCache")
    @PreAuthorize("hasAuthority('btn.config.edit')")
    public R refreshCache() {
        configService.resetConfigCache();
        return R.ok().message("刷新成功");
    }
}

