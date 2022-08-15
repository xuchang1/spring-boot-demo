package com.xc.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2022/1/9 15:25
 */
@RestController
public class HelloController {

	@GetMapping("testLimit")
	public String testLimit() {
		return "1";
	}

	@GetMapping("testLimit2")
	public String testLimit2() {
		return "1";
	}

	@GetMapping("testLimit3")
	public String testLimit3() {
		return "1";
	}
}
