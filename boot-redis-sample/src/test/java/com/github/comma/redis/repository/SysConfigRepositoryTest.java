package com.github.comma.redis.repository;

import com.github.comma.redis.RedisApplication;
import com.github.comma.redis.entity.SysConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysConfigRepositoryTest {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Test
    public void findAll(){
        List<SysConfig> all = sysConfigRepository.findAll();
        System.out.println(all);
        Assert.assertNotNull(all);
    }

    @Test
    @Transactional
    public void getOne(){
        SysConfig one = sysConfigRepository.getOne(3L);
        System.out.println(one);
        Assert.assertNotNull(one);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void save() {
        sysConfigRepository.save(new SysConfig(null ,"16","16",(byte) 1 , "16")) ;
        sysConfigRepository.save(new SysConfig(null ,"17","17",(byte) 1 , "17")) ;
    }

    @Test
    @Transactional
    @Rollback(false)
    public void update(){


        SysConfig sysConfig = sysConfigRepository.getOne(1L) ;
        sysConfig.setRemark("111");
        sysConfigRepository.saveAndFlush(sysConfig);
        System.out.println(22);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void delete(){
        sysConfigRepository.deleteById(16L);
    }


}