package com.github.comma.redis.service.impl;

import com.github.comma.redis.RedisApplication;
import com.github.comma.redis.entity.SysConfig;
import com.github.comma.redis.service.SysConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SysConfigServiceImplTest {

    @Autowired
    private SysConfigService sysConfigService;

    @Test
    public void save() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setDelFlag((byte)1);
        sysConfig.setParamKey("");
        sysConfigService.save(sysConfig);
    }

    @Test
    public void update() {
        SysConfig byId = sysConfigService.findById(1L);
        System.out.println(byId);
        byId.setRemark("333");
        sysConfigService.update(byId) ;
    }

    @Test
    public void delete() {
        sysConfigService.delete(1L);
    }

    @Test
    public void findById() {
        System.out.println(sysConfigService.findById(1L));
        System.out.println(sysConfigService.findById(1L));
        System.out.println(sysConfigService.findById(2L));
    }


    @Test
    public void findByParamKey() {
        System.out.println(sysConfigService.findByParamKey("11"));
        System.out.println(sysConfigService.findByParamKey("11"));
        System.out.println(sysConfigService.findByParamKey("22"));
    }

    @Test
    public void findByParamKeyLike() {
        List<SysConfig> byParamKeyLike = sysConfigService.findByParamKeyLike("%1%");
        System.out.println(byParamKeyLike);
    }


    @Test
    public void test(){


    }
}