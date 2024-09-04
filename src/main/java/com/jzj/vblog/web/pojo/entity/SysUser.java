package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别（0：男 1：女 ）")
    private String sex;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "头像地址")
    private String headUrl;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "岗位id")
    private Long postId;

    @ApiModelProperty(value = "微信openId")
    private String openId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "删除标记（0:不可用 1:可用）")
    private String isDeleted;


}
