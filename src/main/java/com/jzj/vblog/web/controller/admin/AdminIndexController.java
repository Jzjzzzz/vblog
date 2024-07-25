package com.jzj.vblog.web.controller.admin;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.sign.JwtUtils;
import com.jzj.vblog.utils.sign.VerifyCodeUtils;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.vo.UserUpdateVo;
import com.jzj.vblog.web.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author Jzj
 * @Date 2024/5/8 下午4:38
 * @Version 1.0
 * @Message: 后台管理用户登录相关控制器
 */
@Api(tags = "后台管理用户登录相关")
@RestController
@RequestMapping("/admin/index")
public class AdminIndexController extends BaseController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("获取行为验证码")
    @PostMapping({"/get"})
    public R get(@RequestBody CaptchaVO data, HttpServletRequest request) {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(VerifyCodeUtils.getRemoteId(request));
        return R.ok(captchaService.get(data));
    }

    @ApiOperation("验证码校验")
    @PostMapping({"/check"})
    public R check(@RequestBody CaptchaVO data, HttpServletRequest request) {
        data.setBrowserInfo(VerifyCodeUtils.getRemoteId(request));
        try {
            ResponseModel response = captchaService.check(data);
            return R.ok(response);
        } catch (Exception e) {
            return R.error("验证码校验异常");
        }
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public R info(HttpServletRequest request) {
        String username = JwtUtils.getSubject(request.getHeader("token"), JwtUtils.USERNAME);
        Map<String, Object> map = sysUserService.info(username);
        return R.ok(map);
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改个人信息")
    @PutMapping
    public R edit(@Validated @RequestBody UserUpdateVo vo) {
        return toAjax(sysUserService.updateUser(vo));
    }
}
