package com.jzj.vblog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Jzj
 */
@SpringBootApplication
@EnableRetry
@Slf4j
public class VblogApplication {
    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(VblogApplication.class, args);
        Environment env = run.getEnvironment();
        log.info("启动成功!");
        log.info("地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
