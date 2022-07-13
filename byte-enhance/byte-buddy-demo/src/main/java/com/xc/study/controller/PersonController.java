package com.xc.study.controller;

import com.xc.study.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("say")
    public String say() {
        Person person = new Person("xc", 18);
        return person.say("hello");
    }
}
