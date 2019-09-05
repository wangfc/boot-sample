package com.github.comma.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.comma.mybatis.plus.entity.Test;

import java.util.List;


public interface TestService extends IService<Test> {

    List<Test> list();
}
