package com.example.redistest.config.redis.connection;

import com.example.redistest.config.redis.connection.property.StandaloneProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StandaloneConnectionConfig implements RedisConnectionConfig {

    private final StandaloneProperty standaloneProperty;

    @Override
    public RedisConnectionFactory getConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(standaloneProperty.getHost(), standaloneProperty.getPort());

        Optional.ofNullable(standaloneProperty.getUsername())
                .ifPresent(config::setUsername);

        Optional.ofNullable(standaloneProperty.getPassword())
                .ifPresent(config::setPassword);

        if (standaloneProperty.isSSL()) {
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
