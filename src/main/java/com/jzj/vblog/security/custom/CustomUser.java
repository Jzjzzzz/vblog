package com.jzj.vblog.security.custom;

import com.jzj.vblog.web.pojo.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>
 * 自定义用户
 * </p>
 *
 * @author Jzj
 * @since 2022/8/22 15:36
 */
public class CustomUser extends User {

    private SysUser sysUser;

    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }
}
