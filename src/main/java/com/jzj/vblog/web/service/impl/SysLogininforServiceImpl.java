package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.web.pojo.entity.SysLogininfor;
import com.jzj.vblog.web.mapper.SysLogininforMapper;
import com.jzj.vblog.web.service.SysLogininforService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements SysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        logininforMapper.insertLogininfor(logininfor);
    }


}
