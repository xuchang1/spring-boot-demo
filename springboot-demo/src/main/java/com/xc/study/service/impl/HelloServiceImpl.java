package com.xc.study.service.impl;

import com.xc.study.annotation.RateLimitPerSec;
import com.xc.study.service.HelloService;
import com.xc.study.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    @RateLimitPerSec(isSync = false)
    public void doSomething(String id) {
        SleepUtils.sleep(1);
        log.info("方法执行 : {}", id);
    }
}
