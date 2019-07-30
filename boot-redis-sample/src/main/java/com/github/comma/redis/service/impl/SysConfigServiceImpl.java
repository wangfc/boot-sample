package com.github.comma.redis.service.impl;

import com.github.comma.redis.entity.SysConfig;
import com.github.comma.redis.repository.SysConfigRepository;
import com.github.comma.redis.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/30
 */
@Service
@Slf4j
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired(required = false)
    private SysConfigService sysConfigService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysConfig save(SysConfig sysConfig) {

        return sysConfigRepository.save(sysConfig) ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching( evict = {
            @CacheEvict( cacheNames ="SysConfig:id", key = "#p0.id" ),
            @CacheEvict( cacheNames = "SysConfig:paramKey",key = "#p0.paramKey")
    })
    public SysConfig update(SysConfig sysConfig) {
        log.info("update->params:{}",sysConfig);
        return sysConfigRepository.saveAndFlush(sysConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching( evict = {
            @CacheEvict( cacheNames ="SysConfig:id", key = "#id" ),
            @CacheEvict( cacheNames = "SysConfig:paramKey",allEntries = true)
    })
    public SysConfig delete(Long id) {

        SysConfig one = sysConfigService.findById(id);
        if (one == null ){
            throw new RuntimeException("记录不存在 ") ;
        }
        one.setDelFlag((byte)0);
        return sysConfigRepository.saveAndFlush(one);
    }

    @Override
    @Cacheable( cacheNames ="SysConfig:id", key = "#id" )
    public SysConfig findById(Long id) {
        log.info("load from database,id:{}",id);
        return sysConfigRepository.findById(id).get();
    }

    @Override
    @Cacheable( cacheNames = "SysConfig:paramKey",key = "#paramKey")
    public SysConfig findByParamKey(String paramKey) {
        log.info("load from database,paramKey:{}",paramKey);
        return sysConfigRepository.findByParamKey(paramKey) ;
    }

    @Override
    public List<SysConfig> findByParamKeyLike(String paramKey) {
        return sysConfigRepository.findByParamKeyLike(paramKey );
    }
}
