package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30 11:12
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getPageList(SysUser sysUser);
}
