package com.xc.study.service.impl;

import com.xc.study.service.LimitService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author changxu13
 * @date 2021/11/17 14:19
 */
@Service
public class LimitServiceImpl implements LimitService {

	/**
	 * 请求数量阈值
	 */
	private int limit = 10;

	/**
	 * 限时
	 */
	private int intervalTime = 5000;

	/**
	 * redis key
	 */
	private String key = "limit";

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean accessByZSort() {
		long currentTime = System.currentTimeMillis();

		if (stringRedisTemplate.hasKey(key)) {
			// 一端时间类的请求数量（即一定范围类redis的数据数量）
			int currentRequest = stringRedisTemplate.opsForZSet().rangeByScore("limit", currentTime - intervalTime, currentTime).size();
			System.out.println(currentRequest);
			// 超过阈值，禁止访问
			if (currentRequest > limit) {
				// 移除之前的数据
				stringRedisTemplate.opsForZSet().removeRangeByScore(key, 0, currentTime - intervalTime);
				return false;
			}
		}
		stringRedisTemplate.opsForZSet().add(key, UUID.randomUUID().toString(), currentTime);
		return true;
	}

	@Override
	public boolean accessBySetNx() {
		return false;
	}

	@Override
	public boolean accessByTokenBucket() {
		return false;
	}
}
