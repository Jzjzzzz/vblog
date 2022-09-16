package com.jzj.vblog.utils.sign;

import java.util.regex.Pattern;

/**
 * @Author Jzj
 * @Date 2022/9/16 15:24
 * @Version 1.0
 * @Message: 邮箱工具类
 */
public class EmailUtil {
    /**
     * 校验邮箱
     *
     * @param email
     * @return [a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+
     */
    public static boolean isEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }
}
