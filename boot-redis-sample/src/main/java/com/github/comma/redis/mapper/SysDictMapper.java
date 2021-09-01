package com.github.comma.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.comma.redis.entity.SysDict;

import java.util.List;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 9:44
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> list();
}