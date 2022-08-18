package com.jzj.vblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Jzj
 */
@SpringBootApplication
@EnableRetry
public class VblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(VblogApplication.class, args);
    }
}
