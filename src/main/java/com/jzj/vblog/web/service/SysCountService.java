package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysCount;
import com.jzj.vblog.web.pojo.vo.BeforeDayCountVo;

/**
 * <p>
 * 统计归档 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
public interface SysCountService extends IService<SysCount> {

    /**
     * 获取首页统计数据
     * @return
     */
    SysCount getCount();


    /**
     * 获取过去七天日统计数据
     * @return
     */
    BeforeDayCountVo getSevenCount();
}
