package com.github.comma.redis.service.impl;

import com.github.comma.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test() {
        redisService.test();
    }

}