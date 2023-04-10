package com.example.redistest.config.factory;

import com.example.redistest.config.RedisConfig;

public abstract class RedisConfigFactory {
    public RedisConfig newInsatance() {
        RedisConfig redisConfig = createRedisConfig();
        redisConfig.redisTemplate();
        return redisConfig;
    }
    protected abstract RedisConfig createRedisConfig();
}
