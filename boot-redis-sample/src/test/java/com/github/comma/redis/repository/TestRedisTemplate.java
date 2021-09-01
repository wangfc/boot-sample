package com.github.comma.redis.repository;

import com.alibaba.fastjson.JSON;
import com.github.comma.redis.RedisApplication;
import com.github.comma.redis.entity.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wangfc
 * @description
 * @date 2019-09-12 21:36
 */
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Test
    @Transactional
    public void testForValue() {
        sysConfigRepository.deleteById(1L);

        SysConfig sysConfig = sysConfigRepository.findById(2L).get();
        log.info(" sysConfig :{} ", sysConfig);

        redisTemplate.opsForValue().set("sysConfig:" + sysConfig.getId(), sysConfig);
        System.out.println(redisTemplate.opsForValue().get("aa"));
        redisTemplate.opsForHash().put("sysConfig", sysConfig.hashCode() + "", sysConfig);

    }

    @Test
    public void test() {

        SysConfig sysConfig = sysConfigRepository.findById(2L).get();
        /*log.info(" sysConfig :{} " , sysConfig);
        redisTemplate.opsForValue().set("sysConfig:"+sysConfig.getId() , sysConfig);*/
        //System.out.println(redisTemplate.opsForValue().get("aa"));
//        redisTemplate.opsForHash().put("sysConfig" , sysConfig.hashCode()+"" , sysConfig );

        BeanUtilsHashMapper beanUtilsHashMapper = new BeanUtilsHashMapper(SysConfig.class);
        Map<String, Object> map = beanUtilsHashMapper.toHash(sysConfig);
        /*
        redisTemplate.opsForHash().putAll("hash:sysConfig:" + sysConfig.getId() ,map );*/


        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(SysConfig.class);
        List list = new LinkedList<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            list.add(propertyDescriptor.getName());
        }

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("hash:sysConfig:" + sysConfig.getId());

        Object o = beanUtilsHashMapper.fromHash(entries);

        log.info(JSON.toJSONString(entries));

        log.info(JSON.toJSONString(o));

        List<Object> objects = redisTemplate.opsForHash().multiGet("hash:sysConfig:" + sysConfig.getId(), list);
        log.info(JSON.toJSONString(objects));

    }


    @Test
    public void testList() {
        ListOperations<String, Object> stringObjectListOperations =
                redisTemplate.opsForList();


    }

}