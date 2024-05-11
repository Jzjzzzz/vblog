package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.sign.MD5Utils;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysUser;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.pojo.vo.UserAddRoleVo;
import com.jzj.vblog.web.service.SysUserRoleService;
import com.jzj.vblog.web.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @ApiOperation("分页列表")
    @GetMapping
    public TableDataInfo pageList(SysUser sysUser) {
        startPage();
        List<SysUser> list = sysUserService.pageList(sysUser);
        return getDataTable(list);
    }

    @Log(title = "用户表管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增用户表")
    @PostMapping
    public R add(@Validated @RequestBody SysUser sysUser) {
        return toAjax(sysUserService.add(sysUser));
    }

    @ApiOperation("根据用户表id获取用户表详细信息")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable String id) {
        return R.ok(sysUserService.selectById(id));
    }

    @ApiOperation("修改用户表")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@Validated @RequestBody SysUser sysUser) {
        return toAjax(sysUserService.modify(sysUser));
    }

    @ApiOperation("删除用户表")
    @Log(title = "用户表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(sysUserService.deleteByIds(ids, request));
    }

    @ApiOperation("重置密码")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @PutMapping("/restPassword")
    public R restPassword(@RequestBody SysUser sysUser) {
        sysUser.setPassword(MD5Utils.encrypt(sysUser.getPassword()));
        return toAjax(sysUserService.modify(sysUser));
    }

    @ApiOperation("角色分配")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @PutMapping("/allocationRole")
    public R allocationRole(@RequestBody UserAddRoleVo vo) {
        sysUserRoleService.allocationRole(vo);
        return R.ok();
    }
}

