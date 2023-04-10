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

@Configuration
@EnableRedisHttpSession
public class RedisConfig {
	// @Autowired
	// RedisClusterConfigurationProperties clusterProperties;

	@Value("${spring.data.redis.host}")
	private String host;

	@Value("${spring.data.redis.port}")
	private Integer port;

	@Value("${spring.data.redis.password}")
	private String password;
	/*
	@Bean
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
	*/

	/**
	 * Redis Server 와 통신을 하기 위한 connection 추상화
	 * @return
	 */
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		// stand alone 연결
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
		config.setPassword(password);

		return new LettuceConnectionFactory(config);
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