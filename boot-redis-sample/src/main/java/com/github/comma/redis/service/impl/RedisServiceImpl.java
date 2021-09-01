package com.github.comma.redis.service.impl;

import com.github.comma.redis.entity.SysConfig;
import com.github.comma.redis.repository.SysConfigRepository;
import com.github.comma.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangfc
 * @description
 * @date 2019-09-12 23:50
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Override
    @Transactional
    public void test() {


        sysConfigRepository.deleteById(1L);

        SysConfig sysConfig = sysConfigRepository.findById(2L).get();
        log.info(" sysConfig :{} ", sysConfig);

        redisTemplate.opsForValue().set("sysConfig:" + sysConfig.getId(), sysConfig);
        System.out.println(redisTemplate.opsForValue().get("aa"));
        redisTemplate.opsForHash().put("sysConfig", sysConfig.hashCode() + "", sysConfig);
    }
}