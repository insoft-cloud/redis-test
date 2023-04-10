package com.example.redistest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.redistest.service.RedisSampleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class RedisSampleController {

	@Autowired
	private RedisSampleService redisSampleService;

	/**
	 * redis에 key 값으로 조회
	 * @param key
	 * @return
	 */
	@GetMapping(value = "/redis")
	public ResponseEntity<String> getRedisStringValue(String key) {
		return ResponseEntity.ok(redisSampleService.getRedisStringValue(key));
	}

	/**
	 * redis에 key, value 등록
	 * @param key
	 * @param value
	 * @return
	 */
	@PostMapping(value = "/redis")
	public ResponseEntity<Void> setRedisStringValue(String key, String value) {
		redisSampleService.setRedisStringValue(key, value);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * login 하면서 사용자 정보 (name, value) 등록
	 * @param request
	 * @param name
	 * @param value
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<String>  login(HttpServletRequest request, @RequestParam String name, @RequestParam String value) {
		request.getSession().setAttribute(name, value);
		return ResponseEntity.ok(request.getSession().getId());
	}

	/**
	 * 현재 sessionId 조회
	 * @param session
	 * @return
	 */
	@GetMapping("/sessionId")
	public ResponseEntity<String>  getSessionId(HttpSession session) {
		return ResponseEntity.ok(session.getId());
	}

}