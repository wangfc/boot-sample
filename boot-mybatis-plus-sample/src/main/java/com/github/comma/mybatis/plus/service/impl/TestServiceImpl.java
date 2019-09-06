package com.github.comma.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.comma.mybatis.plus.entity.Test;
import com.github.comma.mybatis.plus.mapper.TestMapper;
import com.github.comma.mybatis.plus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试
 *
 * @author wangfc
 * @date 2019-09-05 23:07
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService{


    @Override
    public List<Test> list() {
        return  baseMapper.list() ;
    }
}