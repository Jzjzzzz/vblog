package com.jzj.vblog.security.custom;

import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.sign.JwtUtils;
import com.jzj.vblog.utils.sign.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Jzj
 * @Date 2024/5/11 下午1:58
 * @Version 1.0
 * @Message: 自定义退出登录处理器
 */
public class CustomLogoutHandler implements LogoutHandler {
    private RedisCache redisCache;

    public CustomLogoutHandler(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    /**
     * 退出登录时删除redis缓存中的token
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");
        if(!JwtUtils.checkToken(token)) throw new BusinessException("无效token");
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        // 删除redis缓存中的token
        redisCache.deleteObject(CacheConstants.LOGIN_TOKEN_KEY+userId);
        redisCache.deleteObject(CacheConstants.VBLOG_AUTH_USER+userId);
        ResponseUtil.out(response, R.ok());
    }
}
