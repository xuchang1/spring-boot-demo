package com.xc.study.controller;

import com.xc.study.po.Person;
import com.xc.study.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("queryPersonById")
    public Person queryPersonById(@RequestParam Integer id) {
        return helloService.queryPersonById(id);
    }

    @GetMapping("deleteById")
    public int deleteById(@RequestParam Integer id) {
        return helloService.deleteById(id);
    }
}
