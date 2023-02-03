package com.jzj.vblog.config;

import com.jzj.vblog.utils.constant.RabbitConstants;
import com.jzj.vblog.web.service.MailSendLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Jzj
 * @Date 2023/1/16 9:37
 * @Version 1.0
 * @Message: RabbitMQ配置类
 */
@Configuration
@Slf4j
public class RabbitConfig {


    @Autowired
    MailSendLogService mailSendLogService;

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((data,ack,cause)->{
            String id = data.getId();
            if(ack){
                mailSendLogService.updateMailSendStatus(id,1);
                log.info(id+":消息发送成功");
            }else {
                mailSendLogService.updateMailSendStatus(id,0);
                log.info(id+":消息发送失败");
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.info("消息发送失败");
            }
        });
        return rabbitTemplate;
    }



    @Bean
    public Queue mailQueue(){
        return new Queue(RabbitConstants.EMAIL_QUEUE,true);
    }

    @Bean
    public DirectExchange mailExchange(){
        return ExchangeBuilder.directExchange(RabbitConstants.EMAIL_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding mailBinding(@Qualifier("mailQueue") Queue queue,@Qualifier("mailExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConstants.EMAIL_ROUTING_KEY);
    }


}
