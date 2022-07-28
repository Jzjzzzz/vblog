package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.SysWebInformationMapper;
import com.jzj.vblog.web.pojo.entity.SysWebInformation;
import com.jzj.vblog.web.service.SysWebInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站基本信息 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-28
 */
@Service
public class SysWebInformationServiceImpl extends ServiceImpl<SysWebInformationMapper, SysWebInformation> implements SysWebInformationService {

    @Autowired
    SysWebInformationMapper sysWebInformationMappereb;

    /**
     * 获取网站基本信息
     * @return
     */
    @Override
    public SysWebInformation selectWebInformationById() {
        SysWebInformation information = sysWebInformationMappereb.selectById(1);
        if(information==null) {
            return new SysWebInformation();
        }
        return information;
    }

    @Override
    public int updateWebInformation(SysWebInformation sysWebInformation) {
        return sysWebInformationMappereb.updateById(sysWebInformation);
    }
}
