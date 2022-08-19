package com.jzj.vblog.web.pojo.vo;

import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/8/19 14:30
 * @Version 1.0
 * @Message: 个人信息修改vo
 */
@Data
public class UserUpdateVo {

    private String avatar;

    private String oldpassword;

    private String newpassword1;

    private String newpassword2;
}
