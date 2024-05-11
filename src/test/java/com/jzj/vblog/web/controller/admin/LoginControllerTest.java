package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.utils.sign.MD5Utils;
import com.jzj.vblog.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Jzj
 * @Date 2022/6/7 17:14
 * @Version 1.0
 * @Message:
 */
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private SysUserService sysUserService;


    public static void main(String[] args) {
        String encrypt = MD5Utils.encrypt("111111");
        System.out.println(encrypt);
    }
}