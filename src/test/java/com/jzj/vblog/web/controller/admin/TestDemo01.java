package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.web.pojo.entity.AdminUser;

/**
 * @Author Jzj
 * @Date 2022/7/8 16:32
 * @Version 1.0
 * @Message:
 */
public class TestDemo01 {
    public static void main(String[] args) {
        AdminUser adminUser = new AdminUser();
        adminUser.setAvatar("1111");
        adminUser.setId(2L);
        System.out.println(adminUser.getId());
    }
}
