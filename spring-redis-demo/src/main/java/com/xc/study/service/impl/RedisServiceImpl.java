package com.xc.study.service.impl;

import com.xc.study.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author changxu13
 * @date 2022/1/27 12:52
 */
@Service
public class RedisServiceImpl implements RedisService {
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	private String key = "hello";

	@Override
	public String queryValue() {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void saveValue(String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
}
