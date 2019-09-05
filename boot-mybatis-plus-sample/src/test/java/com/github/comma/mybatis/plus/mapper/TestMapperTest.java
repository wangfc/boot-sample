package com.github.comma.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapperTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void list() throws Exception {
        QueryWrapper<com.github.comma.mybatis.plus.entity.Test> wrapper =  new QueryWrapper() ;
//        wrapper.eq("name" , "a") .eq("age" , 1) ;
        Page<com.github.comma.mybatis.plus.entity.Test> page = new Page<>(1,2) ;

        IPage<com.github.comma.mybatis.plus.entity.Test> testIPage = testMapper.selectPage(page, wrapper);
        System.out.println(testIPage);
    }

}