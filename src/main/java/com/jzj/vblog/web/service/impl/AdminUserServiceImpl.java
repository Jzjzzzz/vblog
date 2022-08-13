package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.result.Assert;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.JwtUtils;
import com.jzj.vblog.utils.sign.MD5Utils;
import com.jzj.vblog.web.mapper.AdminUserMapper;
import com.jzj.vblog.web.pojo.entity.AdminUser;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.pojo.vo.UserInfoVo;
import com.jzj.vblog.web.service.AdminUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员用户表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-06-07
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Override
    public String login(LoginVo vo) {
        //账号
        String username = vo.getUsername();
        Assert.notEmpty(username, ResponseEnum.LOGIN_USER_NULL_ERROR);
        //密码
        String password = vo.getPassword();
        Assert.notEmpty(password, ResponseEnum.LOGIN_PASSWORD_NULL_ERROR);

        AdminUser user = baseMapper.login(username, MD5Utils.encrypt(password));
        //账号密码错误
        if (user == null) {
            throw new BusinessException(ResponseEnum.LOGIN_USER_ERROR);
        }
        UserInfoVo userinfo = new UserInfoVo();
        BeanUtils.copyProperties(user, userinfo);
        userinfo.setName(user.getUsername());

        //封装token
        String token = JwtUtils.getJwtToken(userinfo);
        return token;
    }

    @Override
    public UserInfoVo info(String token) {
        UserInfoVo user = JwtUtils.getUserByJwtToken(token);
        return user;
    }
}
