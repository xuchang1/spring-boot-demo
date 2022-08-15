package com.xc.study.controller;

import com.xc.study.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("test1")
    public String test1(){
        return helloService.test1Service();
    }

    @GetMapping("test2")
    public String test2(String id){
        return helloService.test2Service(id);
    }

    @GetMapping("test3")
    public void test3(){
        helloService.test3Service();
    }
}
