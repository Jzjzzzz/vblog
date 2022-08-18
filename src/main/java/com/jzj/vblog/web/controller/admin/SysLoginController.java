package com.jzj.vblog.web.controller.admin;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jzj
 * @Date 2022/6/7 14:53
 * @Version 1.0
 * @Message: 登录控制器
 */
@Api(tags = "后台管理员登录")
@RestController
@RequestMapping("/admin")
public class SysLoginController {

    @Autowired
    AdminUserService adminUserService;

    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo vo) {
        SaTokenInfo tokenInfo = adminUserService.login(vo);
        return R.ok(tokenInfo.tokenName, tokenInfo.tokenValue);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }

}
