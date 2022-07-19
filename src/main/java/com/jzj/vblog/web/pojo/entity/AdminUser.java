package com.jzj.vblog.web.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 管理员用户表
 * </p>
 *
 * @author Jzj
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value="AdminUser对象", description="管理员用户表")
public class AdminUser extends BaseEntity implements Serializable {
    private String username;

    private String password;

    private String phone;

    private String avatar;

    private String roles;

    private String introduction;


}
