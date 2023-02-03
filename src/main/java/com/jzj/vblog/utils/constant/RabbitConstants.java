package com.jzj.vblog.utils.constant;

/**
 * @Author Jzj
 * @Date 2023/1/16 9:39
 * @Version 1.0
 * @Message: RabbitMQ常量池
 */
public interface RabbitConstants {
    String EMAIL_QUEUE = "email.queue";

    String EMAIL_EXCHANGE = "email.exchange";

    String EMAIL_ROUTING_KEY = "email.routing.key";

    /**
     * 消息投递中
     */
    Integer DELIVERING = 0;

    /**
     * 消息投递成功
     */
    Integer SUCCESS = 1;

    /**
     * 消息投递失败
     */
    Integer FAILURE = 2;

    /**
     * 最大重试次数
     */
    Integer MAX_TRY_COUNT =3;

    /**
     * 消息超时时间
     */
    Integer MSG_TIMEOUT = 1;

}
