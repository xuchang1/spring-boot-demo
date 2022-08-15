package com.xc.study.service;

import com.xc.study.po.KeyCount;
import com.xc.study.po.Person;

import java.util.List;


public interface HelloService {
    Person say(String id);

    List<KeyCount> say1();
}
