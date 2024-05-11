package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysMenu;
import com.jzj.vblog.web.pojo.vo.AssignMenuVo;
import com.jzj.vblog.web.pojo.vo.RouterVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
    * 分页查询
    * @param sysMenu 查询参数
    * @return 列表
    */
    List<SysMenu> pageList(SysMenu sysMenu);

    /**
    * 新增菜单表
    * @param sysMenu 实体
    * @return 成功条数
    */
    int add(SysMenu sysMenu);

    /**
    * 根据id查询单条记录
    * @param id
    */
    SysMenu selectById(String id);

    /**
    * 修改菜单表
    * @param sysMenu 实体
    * @return 成功条数
    */
    int modify(SysMenu sysMenu);

    /**
    * 批量删除菜单表
    * @param ids id数组
    * @return 成功条数
    */
    int deleteByIds(List<String> ids, HttpServletRequest request);

    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();

    /**
     * 根据角色获取授权权限数据
     * @return
     */
    List<SysMenu> findSysMenuByRoleId(String roleId);

    /**
     * 保存角色权限
     * @param  assignMenuVo
     */
    void doAssign(AssignMenuVo assignMenuVo);

    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<RouterVo> findUserMenuList(String userId);

    /**
     * 获取用户按钮权限
     * @param userId
     * @return
     */
    List<String> findUserPermsList(String userId);
}
