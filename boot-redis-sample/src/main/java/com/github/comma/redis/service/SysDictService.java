package com.github.comma.redis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.comma.redis.entity.SysDict;

import java.util.List;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 9:52
 */
public interface SysDictService {

    void load();

    SysDict get(Integer id);

    int save(SysDict sysDict);

    int update(SysDict sysDict);

    int delete(Integer id);

    IPage<SysDict> page(String name, String type, String code, int pageNum, int pageSize);

    List<SysDict> list(String name, String type, String code);
}
