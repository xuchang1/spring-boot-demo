package com.xc.study.service.impl;

import com.xc.study.entity.po.Person;
import com.xc.study.mapper.PersonMapper;
import com.xc.study.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person getPerson(String id) {
        return personMapper.selectById(id);
    }
}
