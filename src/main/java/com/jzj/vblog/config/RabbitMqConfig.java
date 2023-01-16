package com.jzj.vblog.config;

import com.jzj.vblog.utils.constant.RabbitConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Jzj
 * @Date 2023/1/16 9:37
 * @Version 1.0
 * @Message: RabbitMQ配置类
 */
@Configuration
public class RabbitMqConfig {

    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(RabbitConstants.EMAIL_QUEUE).build();
    }
}
