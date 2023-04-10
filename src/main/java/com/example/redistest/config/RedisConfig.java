package com.example.redistest.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {
	// @Autowired
	// RedisClusterConfigurationProperties clusterProperties;

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

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		// stand alone 연결
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
		return lettuceConnectionFactory;
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}