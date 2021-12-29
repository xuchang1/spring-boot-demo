package com.xc.study.controller;

import com.xc.study.service.LimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 基于redis，实现限流
 *
 * @author changxu13
 * @date 2021/11/17 14:17
 */
@RestController
public class LimitController {

	@Resource
	private LimitService limitService;

	@GetMapping("limitTest")
	public String limitTest() {
		return limitService.accessByZSort()? "访问成功":"被限流了";
	}
}
