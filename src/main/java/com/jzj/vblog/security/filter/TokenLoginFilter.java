package com.jzj.vblog.security.filter;

import com.alibaba.fastjson.JSON;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jzj.vblog.security.custom.CustomUser;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.JwtUtils;
import com.jzj.vblog.utils.sign.ResponseUtil;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author Jzj
 * @Date 2024/5/8 下午2:07
 * @Version 1.0
 * @Message: 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisCache redisCache;

    private CaptchaService captchaService;

    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisCache redisCache, CaptchaService captchaService) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/index/login", "POST"));
        this.redisCache = redisCache;
        this.captchaService = captchaService;
    }

    /**
     * 登录校验
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginVo loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginVo.class);
            //行为验证码二次校验
            if (StringUtils.isBlank(loginVo.getCode())) throw new BusinessException(ResponseEnum.CODE_ERROR);
            CaptchaVO captchaVO = new CaptchaVO();
            captchaVO.setCaptchaVerification(loginVo.getCode());
            ResponseModel resCode = captchaService.verification(captchaVO);
            if (!"0000".equals(resCode.getRepCode())) {
                throw new BusinessException(ResponseEnum.CODE_ERROR);
            }
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录成功
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        //注:这里别把权限列表放入jwt中,这会导致生成的token过长,http的header有长度限制.
        String token = JwtUtils.getJwtToken(customUser);
        //将token放入缓存
        HashMap<String, String> redisMap = new HashMap<>();
        redisMap.put("token", token);
        redisMap.put("authorities", JSON.toJSONString(customUser.getAuthorities()));
        redisCache.setCacheMap(CacheConstants.LOGIN_TOKEN_KEY + customUser.getSysUser().getId(), redisMap, 2, TimeUnit.HOURS);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, R.ok(map));
    }

    /**
     * 登录失败
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e.getCause() instanceof RuntimeException) {
            ResponseUtil.out(response, R.error().code(204).message(e.getMessage()));
        } else {
            ResponseUtil.out(response, R.error().code(204).message("账号密码错误"));
        }
    }
}
