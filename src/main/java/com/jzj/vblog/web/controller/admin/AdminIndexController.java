package com.jzj.vblog.web.controller.admin;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.JwtHelper;
import com.jzj.vblog.utils.sign.VerifyCodeUtils;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.SysUser;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.pojo.vo.UserUpdateVo;
import com.jzj.vblog.web.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo vo) {
        //行为验证码二次校验
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(vo.getCode());
        ResponseModel response = captchaService.verification(captchaVO);
        if (!"0000".equals(response.getRepCode())) {
            throw new BusinessException(ResponseEnum.CODE_ERROR);
        }
        //登录校验
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, vo.getUsername()));
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return R.ok(map);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public R logout() {
        // StpUtil.logout();
        return R.ok();
    }

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
        return R.ok(captchaService.check(data));
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public R info(HttpServletRequest request) {
        String username = JwtHelper.getUsername(request.getHeader("token"));
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
