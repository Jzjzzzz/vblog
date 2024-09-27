package com.jzj.vblog.security.custom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 密码处理
 * </p>
 *
 * @author Jzj
 * @since 2022/8/22 15:36
 */
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return new BCryptPasswordEncoder().encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(charSequence.toString(), encodedPassword);
    }
}
