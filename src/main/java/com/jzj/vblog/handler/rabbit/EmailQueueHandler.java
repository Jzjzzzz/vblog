package com.jzj.vblog.handler.rabbit;

import com.jzj.vblog.utils.constant.RabbitConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.web.pojo.entity.EmailMessageStruct;
import com.jzj.vblog.web.service.EmailService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @Author Jzj
 * @Date 2023/1/16 9:56
 * @Version 1.0
 * @Message: 发送Email队列处理器
 */
@Component
@Slf4j
public class EmailQueueHandler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisCache redisCache;

    @RabbitListener(queues = RabbitConstants.EMAIL_QUEUE)
    public void ListenerQueue(Message message, Channel channel) throws IOException {
        EmailMessageStruct emailMessageStruct =(EmailMessageStruct) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        Long tag =(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String id =(String) headers.get("spring_returned_message_correlation");
        try {
            if(redisCache.isCacheMapKey("mail_log",id)){
                log.info("消息已消费");
                channel.basicAck(tag,false);
                return;
            }
            emailService.sendMail(emailMessageStruct.getEmail(),emailMessageStruct.getNickName()+"对您留言的回复-"+"来着漫漫长路的博客",emailMessageStruct.getReply());
            redisCache.setCacheMapValue("mail_log",id,"mmcl");
            channel.basicAck(tag,false);
            log.info("发送消息成功");
        } catch (IOException e){
            channel.basicNack(tag,false,true);
            e.printStackTrace();
        }
    }
}
