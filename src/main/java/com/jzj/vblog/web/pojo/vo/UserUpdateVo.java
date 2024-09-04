package com.jzj.vblog.web.pojo.vo;

import lombok.Data;

/**
 * <p>
 * 个人信息修改vo
 * </p>
 *
 * @author Jzj
 * @since 2022/8/19 14:30
 */
@Data
public class UserUpdateVo {

    private String avatar;

    private String oldpassword;

    private String newpassword1;

    private String newpassword2;
}
