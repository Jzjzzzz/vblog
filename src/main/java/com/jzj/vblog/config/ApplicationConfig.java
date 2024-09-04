package com.jzj.vblog.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

/**
 * <p>
 * 程序注解配置
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 13:55
 */
@Configuration
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
