package com.jzj.vblog.security.custom;

import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.utils.sign.JwtUtils;
import com.jzj.vblog.utils.sign.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 自定义退出登录处理器
 * </p>
 *
 * @author Jzj
 * @since 2022/8/22 15:36
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
        String userId = JwtUtils.getSubject(request.getHeader("token"), JwtUtils.USERID);
        // 删除redis缓存中的token
        redisCache.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + userId);
        ResponseUtil.out(response, R.ok());
    }
}
