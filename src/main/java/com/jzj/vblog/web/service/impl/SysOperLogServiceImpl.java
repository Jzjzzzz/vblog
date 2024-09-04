package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.SysOperLogMapper;
import com.jzj.vblog.web.pojo.entity.SysOperLog;
import com.jzj.vblog.web.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22 11:12
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;

    @Override
    public void insertOperlog(SysOperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }

    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);
    }

    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        return operLogMapper.deleteOperLogByIds(operIds);
    }

    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}
