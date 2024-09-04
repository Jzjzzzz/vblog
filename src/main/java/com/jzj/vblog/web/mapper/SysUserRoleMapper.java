package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06 11:12
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void deleteBatchByUserId(@Param("userId") String userId);

    void deleteBatchByRoleId(@Param("roleId") String roleId);
}
