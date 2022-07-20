package com.jzj.vblog.web.controller.admin;


import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.vo.UserInfoVO;
import com.jzj.vblog.web.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;

    @GetMapping("/info")
    @ApiOperation("根据token获取用户信息")
    public R Info(String token){
        UserInfoVO user = adminUserService.info(token);
        return R.ok("data",user);
    }

}

