package com.xc.study.controller;

import com.xc.study.mapper.ds01.PersonMapper01;
import com.xc.study.mapper.ds02.PersonMapper02;
import com.xc.study.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private PersonMapper01 personMapper01;

    @Autowired
    private PersonMapper02 personMapper02;

    @GetMapping
    public List<Person> test() {
        List<Person> list = new ArrayList<>();
        list.add(personMapper01.selectByPrimaryKey(1));
        list.add(personMapper02.selectByPrimaryKey(2));
        return list;
    }
}
