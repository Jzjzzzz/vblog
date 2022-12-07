package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.SysCountMapper;
import com.jzj.vblog.web.pojo.entity.SysCount;
import com.jzj.vblog.web.service.SysCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 统计归档 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
@Service
public class SysCountServiceImpl extends ServiceImpl<SysCountMapper, SysCount> implements SysCountService {

    @Autowired
    private SysCountMapper sysCountMapper;

    /**
     * 获取首页统计数据
     * @return
     */
    @Override
    public SysCount getCount() {
        return sysCountMapper.getBeforeDayCount();
    }
}
