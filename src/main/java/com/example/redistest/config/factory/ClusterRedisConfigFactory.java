package com.example.redistest.config.factory;

import com.example.redistest.config.ClusterRedisConfig;
import com.example.redistest.config.RedisConfig;

public class ClusterRedisConfigFactory extends RedisConfigFactory {

    @Override
    protected RedisConfig createRedisConfig() {
        return new ClusterRedisConfig();
    };
}
