package com.jzj.vblog.security.custom;

import com.jzj.vblog.utils.sign.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author Jzj
 * @Date 2024/5/8 上午11:51
 * @Version 1.0
 * @Message: 密码处理
 */
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utils.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5Utils.encrypt(charSequence.toString()));
    }
}
