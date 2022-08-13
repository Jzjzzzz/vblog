package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员用户表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-06-07
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    AdminUser login(@Param("username") String username, @Param("password") String password);
}
