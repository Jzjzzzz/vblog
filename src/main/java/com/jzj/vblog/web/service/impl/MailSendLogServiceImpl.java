package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.MailSendLogMapper;
import com.jzj.vblog.web.pojo.entity.MailSendLog;
import com.jzj.vblog.web.service.MailSendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 发送消息记录表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2023-01-31
 */
@Service
public class MailSendLogServiceImpl extends ServiceImpl<MailSendLogMapper, MailSendLog> implements MailSendLogService {

    @Autowired
    private MailSendLogMapper mailSendLogMapper;

    @Override
    public Integer updateMailSendStatus(String id,Integer status) {
        mailSendLogMapper.updateMailSendStatus(id,status);
        return null;
    }

    @Override
    public List<MailSendLog> getMailSendLogByStatus() {
        return mailSendLogMapper.getMailSendLogByStatus();
    }

    @Override
    public void updateCount(String id,Date date) {
        mailSendLogMapper.updateCount(id,date);
    }
}
