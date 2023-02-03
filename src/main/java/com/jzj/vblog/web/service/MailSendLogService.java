package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.MailSendLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 发送消息记录表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2023-01-31
 */
public interface MailSendLogService extends IService<MailSendLog> {

    Integer updateMailSendStatus(String id,Integer status);

    List<MailSendLog> getMailSendLogByStatus();

    void updateCount(String id, Date date);
}
