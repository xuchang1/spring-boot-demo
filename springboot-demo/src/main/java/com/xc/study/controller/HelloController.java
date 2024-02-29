package com.xc.study.controller;

import com.xc.study.entity.Person;
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
		return "<script>alert('XSS')</script>";
	}

	@GetMapping("testLimit2")
	public Person testLimit2() {
		Person person = new Person();
		person.setName("<script>alert('XSS')</script>");
		return person;
	}

	@GetMapping("testLimit3")
	public String testLimit3(String id) {
		return id;
	}
}
