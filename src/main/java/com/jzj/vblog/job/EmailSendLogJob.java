package com.jzj.vblog.job;

import com.jzj.vblog.job.base.BaseJob;
import com.jzj.vblog.utils.constant.RabbitConstants;
import com.jzj.vblog.web.pojo.entity.EmailMessageStruct;
import com.jzj.vblog.web.pojo.entity.MailSendLog;
import com.jzj.vblog.web.service.MailSendLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Author Jzj
 * @Date 2023/2/1 9:15
 * @Version 1.0
 * @Message: 邮件发送重试定时计划
 */
@Slf4j
public class EmailSendLogJob implements BaseJob {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailSendLogService mailSendLogService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<MailSendLog> list = mailSendLogService.getMailSendLogByStatus();
        for (MailSendLog sendLog : list) {
            if(sendLog.getCount()>=3){
                //直接设置该条消息发送失败
                mailSendLogService.updateMailSendStatus(sendLog.getId(),2);
            } else {
                mailSendLogService.updateCount(sendLog.getId(),new Date());
                rabbitTemplate.convertAndSend(
                        RabbitConstants.EMAIL_EXCHANGE,
                        RabbitConstants.EMAIL_ROUTING_KEY,
                        new EmailMessageStruct(sendLog.getEmail(),sendLog.getNickName(),sendLog.getReply()),
                        new CorrelationData(sendLog.getId()));
            }
        }
    }
}
