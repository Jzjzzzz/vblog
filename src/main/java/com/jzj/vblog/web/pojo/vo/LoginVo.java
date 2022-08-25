package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/6/7 15:11
 * @Version 1.0
 * @Message: 登录表单提交实体
 */
@Data
@ApiModel(description="登录表单提交对象")
public class LoginVo {

    @ApiModelProperty(value = "用户账号")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "行为验证码Code")
    private String code;
}
