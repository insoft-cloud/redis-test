package com.example.redistest.config.redis.connection;

import com.example.redistest.config.redis.connection.property.ClusterProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClusterConnectionConfig implements RedisConnectionConfig {

    private final ClusterProperty clusterProperty;

    @Override
    public RedisConnectionFactory getConnectionFactory() {
        RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperty.getNodes());

        Optional.ofNullable(clusterProperty.getUsername())
                .ifPresent(config::setUsername);

        Optional.ofNullable(clusterProperty.getPassword())
                .ifPresent(config::setPassword);

        if (clusterProperty.isSSL()) {
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
