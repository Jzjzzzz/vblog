package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysRoleMenu;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07 11:12
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 分页查询
     *
     * @param sysRoleMenu 查询参数
     * @return 列表
     */
    List<SysRoleMenu> pageList(SysRoleMenu sysRoleMenu);

    /**
     * 新增角色菜单
     *
     * @param sysRoleMenu 实体
     * @return 成功条数
     */
    int add(SysRoleMenu sysRoleMenu);

    /**
     * 根据id查询单条记录
     *
     * @param id id
     */
    SysRoleMenu selectById(String id);

    /**
     * 修改角色菜单
     *
     * @param sysRoleMenu 实体
     * @return 成功条数
     */
    int modify(SysRoleMenu sysRoleMenu);

    /**
     * 批量删除角色菜单
     *
     * @param ids id数组
     * @return 成功条数
     */
    int deleteByIds(List<String> ids, HttpServletRequest request);
}
