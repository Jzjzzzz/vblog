package com.jzj.vblog.web.mapper;

import com.jzj.vblog.web.pojo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> getPageList(SysUser sysUser);
}
