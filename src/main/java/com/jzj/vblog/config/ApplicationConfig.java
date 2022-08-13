package com.jzj.vblog.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

/**
 * @author Jzj
 * @Date 2022/7/22 13:55
 * @Version 1.0
 * @Message: 程序注解配置
 */
@Configuration
/**
 * 表示通过aop框架暴露该代理对象,AopContext能够访问
 */
@EnableAspectJAutoProxy(exposeProxy = true)
public class ApplicationConfig {
    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
