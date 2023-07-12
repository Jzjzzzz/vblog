package com.jzj.vblog;

import com.jzj.vblog.utils.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author Jzj
 * @Date 2023/7/12 9:53
 * @Version 1.0
 * @Message:
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisCache redisCache;

}
