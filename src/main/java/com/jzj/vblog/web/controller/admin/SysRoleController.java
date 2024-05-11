package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysRole;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.role.list')")
    public TableDataInfo pageList(SysRole sysRole) {
        startPage();
        List<SysRole> list = sysRoleService.pageList(sysRole);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.role.add')")
    public R add(@Validated @RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.add(sysRole));
    }

    @ApiOperation("根据角色id获取角色详细信息")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('btn.role.list')")
    public R getInfo(@PathVariable String id) {
        return R.ok(sysRoleService.selectById(id));
    }

    @ApiOperation("修改角色")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('btn.role.edit')")
    public R edit(@Validated @RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.modify(sysRole));
    }

    @ApiOperation("删除角色")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.role.del')")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(sysRoleService.deleteByIds(ids, request));
    }

    @ApiOperation("角色列表")
    @GetMapping("/listAll/{userId}")
    @PreAuthorize("hasAuthority('btn.role.list')")
    public R listAll(@PathVariable String userId) {
        Map<String, Object> map = sysRoleService.getRoleListByUserId(userId);
        return R.ok(map);
    }
}

