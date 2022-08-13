package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/6/7 16:43
 * @Version 1.0
 * @Message: 用户信息实体
 */
@ApiModel("用户信息实体")
@Data
public class UserInfoVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("角色")
    private String roles;

    @ApiModelProperty("描述")
    private String introduction;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phone;
}
