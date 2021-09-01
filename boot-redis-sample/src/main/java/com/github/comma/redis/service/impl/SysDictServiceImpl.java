package com.github.comma.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.comma.redis.common.exception.NoRecordException;
import com.github.comma.redis.common.utils.RedisKeyGenerator;
import com.github.comma.redis.common.utils.RedisUtil;
import com.github.comma.redis.entity.SysDict;
import com.github.comma.redis.mapper.SysDictMapper;
import com.github.comma.redis.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 9:53
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl implements SysDictService {

    private static final String TABLE_NAME = "sys_dict";

    private static final String SET_NAME_PREFIX_KEY = RedisUtil.getKey("set", TABLE_NAME, "field:name:");
    private static final String SET_TYPE_PREFIX_KEY = RedisUtil.getKey("set", TABLE_NAME, "field:type:");
    private static final String SET_CODE_PREFIX_KEY = RedisUtil.getKey("set", TABLE_NAME, "field:code:");

    private static final String SET_ALL_PREFIX_KEY = RedisUtil.getKey("set", TABLE_NAME, "all");


    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void load() {
        List<SysDict> sysDicts = sysDictMapper.selectList(null);

        for (SysDict sysDict : sysDicts) {
            String key = RedisKeyGenerator.genKey("hash", TABLE_NAME, sysDict.getId());
            redisTemplate.opsForHash().putAll(key, RedisUtil.toHash(sysDict));

            String setNameKey = SET_NAME_PREFIX_KEY + sysDict.getName();
            String setTypeKey = SET_TYPE_PREFIX_KEY + sysDict.getType();
            String setCodeKey = SET_CODE_PREFIX_KEY + sysDict.getCode();

            redisTemplate.opsForSet().add(setNameKey, sysDict.getId());
            redisTemplate.opsForSet().add(setTypeKey, sysDict.getId());
            redisTemplate.opsForSet().add(setCodeKey, sysDict.getId());

            redisTemplate.opsForSet().add(SET_ALL_PREFIX_KEY, sysDict.getId());
        }
    }

    @Override
    public SysDict get(Integer id) {
        String key = RedisKeyGenerator.genKey("hash", TABLE_NAME, id);
        Map entries = redisTemplate.opsForHash().entries(key);
        SysDict sysDict = RedisUtil.fromHash(entries, SysDict.class);
        if (sysDict == null) {
            log.debug("load from db, id:{}" + id);
            sysDict = sysDictMapper.selectById(id);
        }
        return sysDict;
    }

    @Override
    public int save(SysDict sysDict) {
        int save = sysDictMapper.insert(sysDict);
        String key = RedisKeyGenerator.genKey("hash", TABLE_NAME, sysDict.getId());
        redisTemplate.opsForHash().putAll(key, RedisUtil.toHash(sysDict));

        String setNameKey = SET_NAME_PREFIX_KEY + sysDict.getName();
        String setTypeKey = SET_TYPE_PREFIX_KEY + sysDict.getType();
        String setCodeKey = SET_CODE_PREFIX_KEY + sysDict.getCode();

        redisTemplate.opsForSet().add(setNameKey, sysDict.getId());
        redisTemplate.opsForSet().add(setTypeKey, sysDict.getId());
        redisTemplate.opsForSet().add(setCodeKey, sysDict.getId());
        redisTemplate.opsForSet().add(SET_ALL_PREFIX_KEY, sysDict.getId());

        return save;
    }

    @Override
    public int update(SysDict sysDict) {

        SysDict oldSysDict = this.get(sysDict.getId());

        int update = sysDictMapper.updateById(sysDict);


        String oldSetNameKey = SET_NAME_PREFIX_KEY + oldSysDict.getName();
        String oldSetTypeKey = SET_TYPE_PREFIX_KEY + oldSysDict.getType();
        String oldSetCodeKey = SET_CODE_PREFIX_KEY + oldSysDict.getCode();
        redisTemplate.opsForSet().remove(oldSetNameKey, oldSysDict.getId());
        redisTemplate.opsForSet().remove(oldSetTypeKey, oldSysDict.getId());
        redisTemplate.opsForSet().remove(oldSetCodeKey, oldSysDict.getId());

        String setNameKey = SET_NAME_PREFIX_KEY + sysDict.getName();
        String setTypeKey = SET_TYPE_PREFIX_KEY + sysDict.getType();
        String setCodeKey = SET_CODE_PREFIX_KEY + sysDict.getCode();

        redisTemplate.opsForSet().add(setNameKey, sysDict.getId());
        redisTemplate.opsForSet().add(setTypeKey, sysDict.getId());
        redisTemplate.opsForSet().add(setCodeKey, sysDict.getId());

        String key = RedisKeyGenerator.genKey("hash", TABLE_NAME, sysDict.getId());
        redisTemplate.opsForHash().putAll(key, RedisUtil.toHash(sysDict));

        return update;
    }

    @Override
    public int delete(Integer id) {
        SysDict sysDict = this.get(id);
        if (sysDict == null) {
            throw new NoRecordException();
        }
        String oldSetNameKey = SET_NAME_PREFIX_KEY + sysDict.getName();
        String oldSetTypeKey = SET_TYPE_PREFIX_KEY + sysDict.getType();
        String oldSetCodeKey = SET_CODE_PREFIX_KEY + sysDict.getCode();
        redisTemplate.opsForSet().remove(oldSetNameKey, sysDict.getId());
        redisTemplate.opsForSet().remove(oldSetTypeKey, sysDict.getId());
        redisTemplate.opsForSet().remove(oldSetCodeKey, sysDict.getId());
        redisTemplate.opsForSet().remove(SET_ALL_PREFIX_KEY, sysDict.getId());

        int delete = sysDictMapper.deleteById(id);

        String key = RedisKeyGenerator.genKey("hash", TABLE_NAME, sysDict.getId());
        Set keys = redisTemplate.opsForHash().keys(key);
        redisTemplate.opsForHash().delete(key, keys.toArray());

        return delete;
    }

    @Override
    public IPage<SysDict> page(String name, String type, String code, int pageNum, int pageSize) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.hasLength(name), "name", name)
                .eq(StringUtils.hasLength(type), "type", type)
                .eq(StringUtils.hasLength(type), "code", code)
                .orderByAsc("name", "type", "sort_order");
        Page<SysDict> page = new Page<>(pageNum, pageSize);
        return sysDictMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysDict> list(String name, String type, String code) {
        List<SysDict> result = new LinkedList<>();
        Set<String> set = new TreeSet<>();
        if (StringUtils.hasLength(name)) {
            set.add(SET_NAME_PREFIX_KEY + name);
        }
        if (StringUtils.hasLength(type)) {
            set.add(SET_TYPE_PREFIX_KEY + type);
        }
        if (StringUtils.hasLength(code)) {
            set.add(SET_CODE_PREFIX_KEY + code);
        }


        //Set intersect = redisTemplate.opsForSet().intersect(SET_NAME_PREFIX_KEY + name, Arrays.asList(SET_TYPE_PREFIX_KEY + type, SET_CODE_PREFIX_KEY + code));
        Set intersect = redisTemplate.opsForSet().intersect(SET_ALL_PREFIX_KEY, set);
        intersect.forEach(t -> {
            String key = RedisKeyGenerator.genKey("hash", TABLE_NAME, t);
            Map entries = redisTemplate.opsForHash().entries(key);
            SysDict sysDict = RedisUtil.fromHash(entries, SysDict.class);
            result.add(sysDict);
        });

/*
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper() ;
        queryWrapper.eq(StringUtils.hasLength(name) , "name" ,name )
                    .eq(StringUtils.hasLength(type) , "type" , type )
                    .eq(StringUtils.hasLength(type) ,"code" , code)
                    .orderByAsc("name" , "type" , "sort_order") ;
        List<SysDict> result = sysDictMapper.selectList(queryWrapper); */
        return result;
    }


}