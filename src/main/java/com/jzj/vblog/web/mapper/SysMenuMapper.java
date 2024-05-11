package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> getPageList(SysMenu sysMenu);

    List<SysMenu> findListByUserId(@Param("userId") String userId);
}
