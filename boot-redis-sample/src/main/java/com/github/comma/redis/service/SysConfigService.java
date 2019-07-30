package com.github.comma.redis.service;

import com.github.comma.redis.entity.SysConfig;

import java.util.List;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/30
 */
public interface SysConfigService {

    SysConfig save(SysConfig sysConfig) ;

    SysConfig update(SysConfig sysConfig) ;

    SysConfig delete(Long id) ;

    SysConfig findById(Long id) ;

    SysConfig findByParamKey(String paramKey) ;

    List<SysConfig> findByParamKeyLike(String paramKey);
}
