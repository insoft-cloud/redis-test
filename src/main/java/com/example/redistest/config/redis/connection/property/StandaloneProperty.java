package com.example.redistest.config.redis.connection.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "spring.data.redis")
public class StandaloneProperty {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Boolean ssl;

    public boolean isSSL() {
        return Optional.ofNullable(ssl)
                .orElse(false);
    }

}
