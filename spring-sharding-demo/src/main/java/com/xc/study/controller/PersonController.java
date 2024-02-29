package com.xc.study.controller;

import com.xc.study.entity.po.Person;
import com.xc.study.service.PersonService;
//import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("getPerson")
    private Person getPerson(@RequestParam String id) {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue(Integer.valueOf(id));
        Person person = personService.getPerson(id);
        hintManager.close();
        return person;
    }
}
