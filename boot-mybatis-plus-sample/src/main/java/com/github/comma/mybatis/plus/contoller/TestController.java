package com.github.comma.mybatis.plus.contoller;

import com.github.comma.mybatis.plus.entity.Test;
import com.github.comma.mybatis.plus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试
 *
 * @author wangfc
 * @date 2019-09-05 23:09
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("{id}")
    public Test get(@PathVariable("id") int id) {
        return testService.getById(id) ;
    }

    @GetMapping
    public List<Test> list() {
        return testService.list();
    }
}