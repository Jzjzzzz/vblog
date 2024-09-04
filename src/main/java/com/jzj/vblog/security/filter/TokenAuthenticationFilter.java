package com.jzj.vblog.security.filter;

import com.alibaba.fastjson.JSON;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.sign.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 认证解析token过滤器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private RedisCache redisCache;

    public TokenAuthenticationFilter(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        getAuthentication(request);
        chain.doFilter(request, response);
    }

    private void getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            String userId = JwtUtils.getSubject(token, JwtUtils.USERID);
            String username = JwtUtils.getSubject(token, JwtUtils.USERNAME);
            if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(username)) {
                Map<String, String> cacheMap = redisCache.getCacheMap(CacheConstants.LOGIN_TOKEN_KEY + userId);
                if (!token.equals(cacheMap.get("token"))) throw new BusinessException("无效token");
                List<Map> mapList = JSON.parseArray(cacheMap.get("authorities"), Map.class);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                if (null != mapList && !mapList.isEmpty()) {
                    for (Map map : mapList) {
                        authorities.add(new SimpleGrantedAuthority((String) map.get("authority")));
                    }
                }
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
            }
        }
    }
}
