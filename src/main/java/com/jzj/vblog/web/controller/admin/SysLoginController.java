package com.jzj.vblog.web.controller.admin;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.VerifyCodeUtils;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private CaptchaService captchaService;

    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo vo) {
            //行为验证码二次校验
            CaptchaVO captchaVO = new CaptchaVO();
            captchaVO.setCaptchaVerification(vo.getCode());
            ResponseModel response = captchaService.verification(captchaVO);
            if(!"0000".equals(response.getRepCode())){
                throw new BusinessException(ResponseEnum.CODE_ERROR);
            }
            //登录校验
            SaTokenInfo tokenInfo = adminUserService.login(vo);
            return R.ok(tokenInfo.tokenName, tokenInfo.tokenValue);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }

    /**
     * 获取行为验证码
     * @param data
     * @param request
     * @return
     */
    @PostMapping({"/get"})
    public R get(@RequestBody CaptchaVO data, HttpServletRequest request) {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(VerifyCodeUtils.getRemoteId(request));
        return R.ok(captchaService.get(data));
    }

    /**
     * 验证码校验
     * @param data
     * @param request
     * @return
     */
    @PostMapping({"/check"})
    public R check(@RequestBody CaptchaVO data, HttpServletRequest request) {
        data.setBrowserInfo(VerifyCodeUtils.getRemoteId(request));
        return R.ok(captchaService.check(data));
    }

}
