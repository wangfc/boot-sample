package com.github.comma.redis.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.comma.redis.common.SpringContextHolder;
import com.github.comma.redis.entity.SysDict;
import com.github.comma.redis.mapper.SysDictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysDictServiceTest {

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysDictMapper sysDictMapper;

    @Test
    public void testList() {


        SysDictService bean = SpringContextHolder.getBean(SysDictService.class);
        bean.list("a", "b", "c");
        /*SysDict byId = sysDictService.getById(3);
        System.out.println(JSON.toJSONString(byId));

        List<SysDict> list = sysDictMapper.list();
        System.out.println(JSON.toJSONString(list));*/

    }

    @Test
    public void testPage() {
        IPage<SysDict> page = sysDictService.page("a", "b", "c", 1, 2);
        System.out.println(JSON.toJSONString(page));
    }
}