package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.web.pojo.entity.AdminUser;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.service.AdminUserService;
import org.junit.jupiter.api.Test;
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
    AdminUserService adminUserService;

    @Test
   public void login() {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("admin");
        loginVo.setPassword("111111");
        adminUserService.login(loginVo);
        AdminUser user = new AdminUser();
        
    }
}