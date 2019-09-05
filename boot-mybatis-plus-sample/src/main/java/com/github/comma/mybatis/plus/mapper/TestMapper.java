package com.github.comma.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.comma.mybatis.plus.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试mapper
 *
 * @author wangfc
 * @date 2019-09-05 22:37
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {

    List<Test> list() ;
}