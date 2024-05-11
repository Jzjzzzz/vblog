package com.jzj.vblog.web.pojo.vo;

import lombok.Data;

/**
 * @Author Jzj
 * @Date 2024/5/6 下午5:55
 * @Version 1.0
 * @Message: 用户分配角色Vo
 */
@Data
public class UserAddRoleVo {

    private String userId;

    private String [] roleIds;
}
