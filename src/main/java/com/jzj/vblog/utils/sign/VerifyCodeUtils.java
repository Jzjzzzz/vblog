package com.jzj.vblog.utils.sign;

import com.anji.captcha.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Jzj
 * @Date 2022/8/24 15:23
 * @Version 1.0
 * @Message: 验证码工具类
 */
public class VerifyCodeUtils {

    public static final String getRemoteId(HttpServletRequest request) {
        String xfwd = request.getHeader("X-Forwarded-For");
        String ip = getRemoteIpFromXfwd(xfwd);
        String ua = request.getHeader("user-agent");
        return com.anji.captcha.util.StringUtils.isNotBlank(ip) ? ip + ua : request.getRemoteAddr() + ua;
    }

    private static String getRemoteIpFromXfwd(String xfwd) {
        if (com.anji.captcha.util.StringUtils.isNotBlank(xfwd)) {
            String[] ipList = xfwd.split(",");
            return StringUtils.trim(ipList[0]);
        } else {
            return null;
        }
    }
}
