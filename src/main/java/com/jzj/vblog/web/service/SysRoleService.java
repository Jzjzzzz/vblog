package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysRole;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06 11:12
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询
     *
     * @param sysRole 查询参数
     * @return 列表
     */
    List<SysRole> pageList(SysRole sysRole);

    /**
     * 新增角色
     *
     * @param sysRole 实体
     * @return 成功条数
     */
    int add(SysRole sysRole);

    /**
     * 根据id查询单条记录
     *
     * @param id id
     */
    SysRole selectById(String id);

    /**
     * 修改角色
     *
     * @param sysRole 实体
     * @return 成功条数
     */
    int modify(SysRole sysRole);

    /**
     * 批量删除角色
     *
     * @param ids id数组
     * @return 成功条数
     */
    int deleteByIds(List<String> ids, HttpServletRequest request);

    Map<String, Object> getRoleListByUserId(String userId);

    /**
     * 获取用户角色权限
     *
     * @param id id
     * @return 列表
     */
    List<String> findUserRoleList(String id);
}
