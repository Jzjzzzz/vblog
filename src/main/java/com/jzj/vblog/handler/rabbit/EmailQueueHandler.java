package com.jzj.vblog.handler.rabbit;

import com.jzj.vblog.utils.constant.RabbitConstants;
import com.jzj.vblog.web.pojo.entity.EmailMessageStruct;
import com.jzj.vblog.web.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Jzj
 * @Date 2023/1/16 9:56
 * @Version 1.0
 * @Message: 发送Email队列处理器
 */
@Component
public class EmailQueueHandler {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitConstants.EMAIL_QUEUE)
    public void ListenerQueue(EmailMessageStruct message){
        emailService.sendMail(message.getEmail(),message.getNickName()+"对您留言的回复-"+"来着漫漫长路的博客",message.getReply());
    }
}
