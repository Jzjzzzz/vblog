package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.utils.sign.MD5Utils;
import com.jzj.vblog.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author Jzj
 * @Date 2022/6/7 17:14
 * @Version 1.0
 * @Message:
 */
@SpringBootTest
public class LoginControllerTest {

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("111111");
        System.out.println(encode);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches("111111", "$2a$10$AhJtTxLkpEnhpmNuSzb7ZubX1brN5J5Zmdh.aAh0rT62Tp9yjKjvG");
        System.out.println(matches);
    }
}