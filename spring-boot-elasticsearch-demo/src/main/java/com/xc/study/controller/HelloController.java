package com.xc.study.controller;

import com.xc.study.po.KeyCount;
import com.xc.study.po.Person;
import com.xc.study.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("say")
    public Person say(String id) {
        return helloService.say(id);
    }

    @GetMapping("say1")
    public List<KeyCount> say1() {
        return helloService.say1();
    }

	@GetMapping("index")
	public String index(String id) {
		return id;
	}
}
