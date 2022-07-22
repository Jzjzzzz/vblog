package com.jzj.vblog.web.mapper;

import com.jzj.vblog.web.pojo.entity.SysLogininfor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);
}
