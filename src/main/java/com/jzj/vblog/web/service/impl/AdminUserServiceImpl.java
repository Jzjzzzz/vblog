package com.jzj.vblog.web.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.constant.Constants;
import com.jzj.vblog.utils.result.Assert;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.web.mapper.AdminUserMapper;
import com.jzj.vblog.web.pojo.entity.AdminUser;
import com.jzj.vblog.web.pojo.vo.LoginVo;
import com.jzj.vblog.web.pojo.vo.UserInfoVo;
import com.jzj.vblog.web.pojo.vo.UserUpdateVo;
import com.jzj.vblog.web.service.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public SaTokenInfo login(LoginVo vo) {
        //账号
        String username = vo.getUsername();
        Assert.notEmpty(username, ResponseEnum.LOGIN_USER_NULL_ERROR);
        //密码
        String password = vo.getPassword();
        Assert.notEmpty(password, ResponseEnum.LOGIN_PASSWORD_NULL_ERROR);
        AdminUser user = adminUserMapper.login(username, SaSecureUtil.md5BySalt(password, Constants.MD5_SALT));
        //账号密码错误
        if (user == null) throw new BusinessException(ResponseEnum.LOGIN_USER_ERROR);
        UserInfoVo userinfo = new UserInfoVo();
        BeanUtils.copyProperties(user, userinfo);
        userinfo.setName(user.getUsername());
        //封装token
        StpUtil.login(username, SaLoginConfig
                .setExtra("id", user.getId())
                .setExtra("name", user.getUsername())
                .setExtra("introduction", user.getIntroduction())
                .setExtra("avatar", user.getAvatar())
                .setExtra("roles", user.getRoles()));
        return StpUtil.getTokenInfo();
    }

    @Override
    public UserInfoVo info() {
        UserInfoVo user = new UserInfoVo();
        try {
            user.setName((String) StpUtil.getExtra("name"));
            user.setAvatar((String) StpUtil.getExtra("avatar"));
            user.setIntroduction((String) StpUtil.getExtra("introduction"));
            user.setRoles((String) StpUtil.getExtra("roles"));
            return user;
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.LOGIN_AUTH_ERROR);
        }
    }

    @Override
    public int updateUser(UserUpdateVo vo) {
        AdminUser user = adminUserMapper.selectOne(null);
        if(StringUtils.isNotBlank(vo.getAvatar())){
            user.setAvatar(vo.getAvatar());
        }
        if(StringUtils.isNotBlank(vo.getOldpassword())){
            if(StringUtils.isBlank(vo.getNewpassword1()) && StringUtils.isBlank(vo.getNewpassword2())) throw new BusinessException("新密码不能为空");
            if(!vo.getNewpassword1().equals(vo.getNewpassword2())) throw new BusinessException("两次输入密码不一致");
            //旧密码比对
            if(!user.getPassword().equals(SaSecureUtil.md5BySalt(vo.getOldpassword(),Constants.MD5_SALT))) throw new BusinessException("旧密码错误");
            user.setPassword(SaSecureUtil.md5BySalt(vo.getNewpassword1(),Constants.MD5_SALT));
        }
        return adminUserMapper.updateById(user);
    }
}
