package com.xc.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author changxu13
 * @date 2021/11/17 14:13
 */
@SpringBootApplication
public class RedisMain {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(RedisMain.class, args);
		run.getBeanFactory();
	}
}
