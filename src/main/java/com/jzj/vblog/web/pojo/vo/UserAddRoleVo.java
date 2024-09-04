package com.jzj.vblog.web.pojo.vo;

import lombok.Data;

/**
 * <p>
 * 用户分配角色vo
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
@Data
public class UserAddRoleVo {

    private String userId;

    private String [] roleIds;
}
