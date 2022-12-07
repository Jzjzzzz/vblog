package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysCount;

/**
 * <p>
 * 统计归档 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
public interface SysCountMapper extends BaseMapper<SysCount> {

    /**
     * 获取昨天的统计数据
     * @return
     */
    SysCount getBeforeDayCount();
}
