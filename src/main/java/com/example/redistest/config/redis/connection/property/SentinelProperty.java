package com.example.redistest.config.redis.connection.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.data.redis.sentinel")
public class SentinelProperty {

    private Set<String> nodes;

    private String master;

    private String username;

    private String password;

    private Boolean ssl;

    public boolean isSSL() {
        return Optional.ofNullable(ssl)
                .orElse(false);
    }

}
