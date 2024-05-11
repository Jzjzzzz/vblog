package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.SysUserRoleMapper;
import com.jzj.vblog.web.pojo.entity.SysUserRole;
import com.jzj.vblog.web.pojo.vo.UserAddRoleVo;
import com.jzj.vblog.web.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Transactional
    @Override
    public void allocationRole(UserAddRoleVo vo) {
        baseMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id", vo.getUserId()));
        List<SysUserRole> list = new ArrayList<>();
        Arrays.stream(vo.getRoleIds()).forEach(s->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(s);
            list.add(sysUserRole);
        });
        this.saveBatch(list);
    }
}
