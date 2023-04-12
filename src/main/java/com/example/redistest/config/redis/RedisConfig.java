package com.example.redistest.config.redis;
import com.example.redistest.config.redis.connection.RedisConnectionConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableRedisHttpSession
public class RedisConfig {

    @Value("${spring.data.redis.connection-type}")
    private String connectionType;

    private final RedisConnectionConfig standaloneConnectionConfig;

    private final RedisConnectionConfig sentinelConnectionConfig;

    private final RedisConnectionConfig clusterConnectionConfig;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return switch (connectionType) {
            case "standalone" -> standaloneConnectionConfig.getConnectionFactory();
            case "sentinel" -> sentinelConnectionConfig.getConnectionFactory();
            case "cluster" -> clusterConnectionConfig.getConnectionFactory();
            default -> null;
        };
    }

    /**
     * 오브젝트 serialization 과 connection 수행하는 기본 템플릿
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * Redis key, value가 String인 경우 확장객체 사용
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }

}