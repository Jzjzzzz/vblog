package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.AdminUser;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.pojo.vo.UserInfoVo;

/**
 * <p>
 * 管理员用户表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-06-07
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * 登录
     *
     * @param vo 登录表单提交信息
     * @return 返回token
     */
    String login(LoginVo vo);

    /**
     * 获取用户信息
     *
     * @param token token
     * @return 返回用户信息
     */
    UserInfoVo info(String token);
}
