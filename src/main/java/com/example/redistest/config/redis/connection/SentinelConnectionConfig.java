package com.example.redistest.config.redis.connection;

import com.example.redistest.config.redis.connection.property.SentinelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SentinelConnectionConfig implements RedisConnectionConfig {

    private final SentinelProperty sentinelProperty;

    @Override
    public RedisConnectionFactory getConnectionFactory() {
        RedisSentinelConfiguration config = new RedisSentinelConfiguration()
                .master(sentinelProperty.getMaster());

        sentinelProperty.getNodes()
                .forEach(n ->
                        config.sentinel(RedisNode.fromString(n))
                              .setPassword(sentinelProperty.getPassword()));

        Optional.ofNullable(sentinelProperty.getUsername())
                .ifPresent(config::setUsername);

        Optional.ofNullable(sentinelProperty.getPassword())
                .ifPresent(config::setPassword);

        if (sentinelProperty.isSSL()) {
            LettuceClientConfiguration client = LettuceClientConfiguration.builder()
                    .useSsl()
                    .disablePeerVerification()
                    .and()
                    .commandTimeout(Duration.ofSeconds(2))
                    .shutdownTimeout(Duration.ZERO)
                    .build();

            return new LettuceConnectionFactory(config, client);
        }

        return new LettuceConnectionFactory(config);
    }
}
