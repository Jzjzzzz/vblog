package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysRole;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06 11:12
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getPageList(SysRole sysRole);

    List<SysRole> findListByUserId(@Param("userId") String userId);
}
