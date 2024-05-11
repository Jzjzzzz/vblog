package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysMenu;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.vo.AssignMenuVo;
import com.jzj.vblog.web.service.SysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public R findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return R.ok(list);
    }

    @Log(title = "菜单表管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增菜单表")
    @PostMapping
    public R add(@Validated @RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.add(sysMenu));
    }

    @ApiOperation("根据菜单表id获取菜单表详细信息")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable String id) {
        return R.ok(sysMenuService.selectById(id));
    }

    @ApiOperation("修改菜单表")
    @Log(title = "菜单表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@Validated @RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.modify(sysMenu));
    }

    @ApiOperation("删除菜单表")
    @Log(title = "菜单表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(sysMenuService.deleteByIds(ids, request));
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId) {
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return R.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public R doAssign(@RequestBody AssignMenuVo assignMenuVo) {
        sysMenuService.doAssign(assignMenuVo);
        return R.ok();
    }
}

