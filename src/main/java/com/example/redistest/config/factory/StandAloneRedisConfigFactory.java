package com.example.redistest.config.factory;

import com.example.redistest.config.RedisConfig;
import com.example.redistest.config.StandAloneRedisConfig;

public class StandAloneRedisConfigFactory extends RedisConfigFactory {

    @Override
    protected RedisConfig createRedisConfig() {
        return new StandAloneRedisConfig();
    };
}
