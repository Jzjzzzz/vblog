package com.jzj.vblog.web.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.vo.UserInfoVo;
import com.jzj.vblog.web.pojo.vo.UserUpdateVo;
import com.jzj.vblog.web.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 管理员用户表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-06-07
 */
@RestController
@RequestMapping("/admin/user")
@Api("后台管理员控制器")
@SaCheckLogin
public class SysUserController extends BaseController {

    @Autowired
    AdminUserService adminUserService;

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public R info() {
        UserInfoVo user = adminUserService.info();
        return R.ok("data", user);
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改个人信息")
    @PutMapping
    public R edit(@Validated @RequestBody UserUpdateVo vo) {
        return toAjax(adminUserService.updateUser(vo));
    }

}

