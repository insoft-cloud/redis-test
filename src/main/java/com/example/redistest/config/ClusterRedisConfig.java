package com.example.redistest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class ClusterRedisConfig implements RedisConfig{

    @Autowired
    RedisClusterConfigurationProperties clusterProperties;

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Value("${spring.data.redis.password}")
    private String password;

    public RedisConnectionFactory redisConnectionFactory() {
        // cluster 연결
        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration ();
        clusterProperties.getNodes().forEach(s ->{
            String[] url = s.split(":");
            redisConfig.clusterNode(url[0],Integer.parseInt(url[1]));
        });
        redisConfig.setPassword(password);
        return new LettuceConnectionFactory(redisConfig);

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
