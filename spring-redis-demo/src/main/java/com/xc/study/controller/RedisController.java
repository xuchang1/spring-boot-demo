package com.xc.study.controller;

import com.xc.study.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author changxu13
 * @date 2022/1/27 12:52
 */
@RestController
public class RedisController {

	@Resource
	private RedisService redisService;

	@GetMapping("queryValue")
	public String queryValue() {
		return redisService.queryValue();
	}

	@GetMapping("saveValue")
	public void saveValue(String value) {
		redisService.saveValue(value);
	}
}
