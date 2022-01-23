package com.xc.study.service.impl;

import com.xc.study.mapper.PersonMapper;
import com.xc.study.po.Person;
import com.xc.study.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    private PersonMapper personMapper;

    @Override
    public Person queryPersonById(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }
}
