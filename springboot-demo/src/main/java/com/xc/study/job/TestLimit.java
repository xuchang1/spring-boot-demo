package com.xc.study.job;

import com.xc.study.service.HelloService;
import com.xc.study.util.SleepUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestLimit {

    @Autowired
    private HelloService helloService;

//    @PostConstruct
    public void init() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            helloService.doSomething(String.valueOf(i));
            if (i == 0) {
                SleepUtils.sleep(10);
            }
        }
        System.out.println("耗时 : " + (System.currentTimeMillis() - startTime));
    }

}
