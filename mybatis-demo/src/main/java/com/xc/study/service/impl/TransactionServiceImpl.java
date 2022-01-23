package com.xc.study.service.impl;

import com.xc.study.mapper.PersonMapper;
import com.xc.study.po.Person;
import com.xc.study.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Resource
    private PersonMapper personMapper;

    @Override
    @Transactional
    public void updatePerson() {
        Person person = new Person();
        person.setId(2);
        person.setName("小红");
        personMapper.updateByPrimaryKeySelective(person);

        person.setId(3);
        person.setName(null);
        personMapper.updateByPrimaryKey(person);
    }
}
