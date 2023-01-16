package com.jzj.vblog;

import com.jzj.vblog.utils.constant.RabbitConstants;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Jzj
 * @Date 2023/1/16 9:51
 * @Version 1.0
 * @Message: RabbitMQ测试类
 */
@SpringBootTest
public class RabbitTest {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend(RabbitConstants.EMAIL_QUEUE,"你好");
    }
}
