package com.github.comma.redis.common.utils;

import org.springframework.data.redis.hash.BeanUtilsHashMapper;

import java.util.Map;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 21:50
 */
public final class RedisUtil {

    private RedisUtil() {
        throw new IllegalStateException("RedisUtil can not be null");
    }

    public static String getKey(String prefix, String tableName, Object recordKey) {
        return RedisKeyGenerator.genKey(prefix, tableName, recordKey);
    }

    public static String getKey(String delimiter, String prefix, String tableName, Object recordKey) {
        return RedisKeyGenerator.genKey(delimiter, prefix, tableName, recordKey);
    }

    public static Map<String, String> toHash(Object object) {
        BeanUtilsHashMapper beanUtilsHashMapper = new BeanUtilsHashMapper(object.getClass());
        return beanUtilsHashMapper.toHash(object);
    }


    public static <T> T fromHash(Map<String, String> hash, Class<T> clazz) {
        BeanUtilsHashMapper beanUtilsHashMapper = new BeanUtilsHashMapper(clazz);
        return (T) beanUtilsHashMapper.fromHash(hash);
    }

}