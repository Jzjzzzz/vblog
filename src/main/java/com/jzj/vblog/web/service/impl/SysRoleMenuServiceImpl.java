package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.web.pojo.entity.SysRoleMenu;
import com.jzj.vblog.web.mapper.SysRoleMenuMapper;
import com.jzj.vblog.web.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> pageList(SysRoleMenu sysRoleMenu) {
        List<SysRoleMenu> list = sysRoleMenuMapper.getPageList(sysRoleMenu);
        return list;
    }

    @Override
    public int add(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.insert(sysRoleMenu);
    }

    @Override
    public SysRoleMenu selectById(String id) {
        return sysRoleMenuMapper.selectById(id);
    }

    @Override
    public int modify(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.updateById(sysRoleMenu);
    }

    @Override
    public int deleteByIds(List<String> ids, HttpServletRequest request) {
        return sysRoleMenuMapper.deleteBatchIds(ids);
    }
}
