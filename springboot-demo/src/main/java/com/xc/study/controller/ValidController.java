package com.xc.study.controller;

import com.xc.study.annotation.CustomValidated;
import com.xc.study.entity.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ValidController {

    @GetMapping("say")
    public Person say(@CustomValidated Person person){
        return person;
    }
}
