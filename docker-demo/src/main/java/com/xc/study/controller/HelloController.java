package com.xc.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2021/11/1 10:17
 */
@RestController
public class HelloController {

	@GetMapping("hello")
	public String hello(String id) {
		return "hello : " + id;
	}
}
