package com.github.comma.redis;

import com.github.comma.redis.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 21:12
 */
@Component
@Slf4j
public class Loader implements CommandLineRunner {

    @Autowired
    private SysDictService sysDictService;

    @Override
    public void run(String... args) throws Exception {
        log.info("==========开始加载sys_dict表==========");
        sysDictService.load();
        log.info("==========加载sys_dict表结束==========");


    }
}