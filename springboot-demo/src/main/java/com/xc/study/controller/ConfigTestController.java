package com.xc.study.controller;

import com.xc.study.config.ConfigTest;
import com.xc.study.config.ConfigTest2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxu13
 * @date 2022/1/14 15:45
 */
@RestController
public class ConfigTestController {

	@Autowired
	private ConfigTest configTest;

	@Autowired
	private ConfigTest2 configTest2;

	@GetMapping("testConfig")
	public String testConfig() {
		return configTest.toString();
	}

	@GetMapping("testConfig2")
	public String testConfig2() {
		return configTest2.toString();
	}
}
