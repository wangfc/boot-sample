package com.github.comma.redis.common.utils;

import org.springframework.util.Assert;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 21:37
 */
public final class RedisKeyGenerator {

    private static String DEFAULT_DELIMITER = ":";

    private RedisKeyGenerator() {
        throw new IllegalStateException("RedisKeyGenerator can not be instance");
    }

    public static String genKey(String prefix, String tableName, Object recordKey) {

        return genKey(DEFAULT_DELIMITER, prefix, tableName, recordKey);
    }

    public static String genKey(String delimiter, String prefix, String tableName, Object recordKey) {
        Assert.notNull(prefix, "prefix can not be null ");
        Assert.notNull(tableName, "tableName can not be null ");
        Assert.notNull(recordKey, "recordKey can not be null ");
        return new StringBuilder().append(prefix).append(delimiter).append(tableName).append(delimiter).append(recordKey.toString()).toString();
    }
}