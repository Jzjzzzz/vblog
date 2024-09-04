package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07 11:12
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysRoleMenu> getPageList(SysRoleMenu sysRoleMenu);

    void deleteBatchByRoleId(@Param("roleId") String roleId);
}
