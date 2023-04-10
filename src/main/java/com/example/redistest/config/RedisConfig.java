package com.example.redistest.config;
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

public interface RedisConfig {

	/**
	 * Redis Server 와 통신을 하기 위한 connection 추상화
	 * @return
	 */
	public RedisConnectionFactory redisConnectionFactory() ;

	/**
	 * 오브젝트 serialization 과 connection 수행하는 기본 템플릿
	 * @return
	 */
	public RedisTemplate<String, Object> redisTemplate() ;

	/**
	 * Redis key, value가 String인 경우 확장객체 사용
	 * @return
	 */
	public StringRedisTemplate stringRedisTemplate() ;
}