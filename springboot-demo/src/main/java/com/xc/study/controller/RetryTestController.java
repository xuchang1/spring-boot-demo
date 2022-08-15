package com.xc.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

/**
 * @author changxu13
 * @date 2022/1/17 13:56
 */
@RestController
@Slf4j
@EnableRetry
public class RetryTestController {

	@GetMapping("retryTest")
	@Retryable(value = TimeoutException.class)
	public String retryTest() throws TimeoutException {
		log.info("调用retryTest方法");
		throw new TimeoutException();
	}
}
