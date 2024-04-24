package com.xc.study.controller;

import com.xc.study.entity.Person;
import com.xc.study.feign.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController

public class HelloController {

    @Autowired
    private HelloFeignClient helloFeignClient;

    @GetMapping("get")
    public Person get(){
        return new Person();
    }

    @GetMapping("start")
    public Person start(){
        Mono<Person> mono = helloFeignClient.get();
        return mono.block();
    }
}
