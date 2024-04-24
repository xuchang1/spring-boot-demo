package com.xc.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author changxu13
 * @date 2022/1/9 15:23
 */
@SpringBootApplication
@EnableAsync
public class DemoMain {

	public static void main(String[] args) {
		SpringApplication.run(DemoMain.class, args);
	}
}
