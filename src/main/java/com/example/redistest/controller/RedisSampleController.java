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

	@GetMapping(value = "/redis")
	public ResponseEntity<String> getRedisStringValue(String key) {
		return ResponseEntity.ok(redisSampleService.getRedisStringValue(key));
	}

	@PostMapping(value = "/redis")
	public ResponseEntity<Void> setRedisStringValue(String key, String value) {
		redisSampleService.setRedisStringValue(key, value);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/login")
	public String login(HttpServletRequest request, @RequestParam String name, @RequestParam String value) {
		request.getSession().setAttribute(name, value);
		return request.getSession().getId();
	}

	@GetMapping("/sessionId")
	public String getSessionId(HttpSession session) {
		return session.getId();
	}

}