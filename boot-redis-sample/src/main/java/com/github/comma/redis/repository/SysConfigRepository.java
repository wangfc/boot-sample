package com.github.comma.redis.repository;

import com.github.comma.redis.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
@Repository
public interface SysConfigRepository extends JpaRepository<SysConfig,Long> {

    SysConfig findByParamKey(String paramKey) ;

    List<SysConfig> findByParamKeyLike(String paramKey) ;
}
