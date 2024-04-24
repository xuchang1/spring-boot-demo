package com.xc.study.service.impl;

import com.xc.study.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public CompletableFuture<String> a() {
        throw new RuntimeException("11");
//        sleep(2);
//        log.info("a执行");
//        return CompletableFuture.completedFuture("a");
    }

    @Override
    @Async
    public CompletableFuture<String> b() {
//        throw new RuntimeException("22");
        sleep(2);
        log.info("b执行");
        return CompletableFuture.completedFuture("b");
    }

    @Override
    @Async
    public CompletableFuture<String> c() {
        sleep(2);
        log.info("c执行");
        return CompletableFuture.completedFuture("c");
    }

    @Override
    public String d(String res) {
        sleep(4);
        log.info("d执行");
        return res + "d";
    }

    private void sleep(long time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            log.error("中断");
        }
    }
}
