package com.xc.study.service.impl;

import com.xc.study.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String test1Service() {
        return "test1";
    }

    @Override
    public String test2Service(String id) {
        return id;
    }

    @Override
    public void test3Service() {
        innerMethod();
    }

    public void innerMethod() {

    }
}
