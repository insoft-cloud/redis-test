package com.example.redistest.config.redis.connection;

import org.springframework.data.redis.connection.RedisConnectionFactory;

public interface RedisConnectionConfig {

    public RedisConnectionFactory getConnectionFactory();

}
