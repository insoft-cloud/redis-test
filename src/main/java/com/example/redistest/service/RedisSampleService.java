package com.example.redistest.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSampleService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * redis에 key 조회
	 * @param key
	 * @return
	 */
	public String getRedisStringValue(String key) {
		ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
		System.out.println("Redis key : " + key);
		System.out.println("Redis value : " + stringValueOperations.get(key));
		return stringValueOperations.get(key);
	}

	/**
	 * redis에 key, value 등록
	 * @param key
	 * @param value
	 */
	public void setRedisStringValue(String key, String value) {
		ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
		stringValueOperations.set(key, value);
		System.out.println("Redis key : " + key);
		System.out.println("Redis value : " + stringValueOperations.get(key));
	}
}