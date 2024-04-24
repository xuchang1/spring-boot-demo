package com.xc.study.controller;

import com.xc.study.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("testAsync")
    public List<String> testAsync() {
        List<String> result = new CopyOnWriteArrayList<>();

        CompletableFuture<String> a = asyncService.a();
        CompletableFuture<Void> d = a.whenComplete((res, ex) -> {
            if (null == ex) {
                log.info("a返回处理" + res);
                result.add(res);
            }
        }).thenAccept(res -> {
            log.info("触发d方法调用");
            String res2 = asyncService.d(res);
            log.info("d返回处理" + res2);
            result.add(res2);
        });

        CompletableFuture<String> b = asyncService.b();
        b.thenAccept(res -> {
            log.info("b返回处理" + res);
            result.add(res);
        });

        CompletableFuture<String> c = asyncService.c();
        c.thenAccept(res -> {
            log.info("c返回处理" + res);
            result.add(res);
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(d, b, c);
        try {
            allOf.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException ex1) {
            log.error("部分任务超时未完成");
        } catch (Exception ex2) {
            log.error("部分任务超时未完成");
        }
        return result;
    }

    @GetMapping("testAsync2")
    public List<String> testAsync2() {
        List<String> result = new CopyOnWriteArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(4);

        CompletableFuture<String> a = asyncService.a();
        a.whenComplete((res, ex) -> {
            countDownLatch.countDown();
            if (null == ex) {
                log.info("a返回处理" + res);
                result.add(res);
            }
        }).whenComplete((res, ex) -> {
            if (ex != null) {
                countDownLatch.countDown();
                return;
            }
            try {
                log.info("触发d方法调用");
                String res2 = asyncService.d(res);
                log.info("d返回处理" + res2);
                result.add(res2);
            } finally {
                countDownLatch.countDown();
            }}
        );

        CompletableFuture<String> b = asyncService.b();
        b.whenComplete((res, ex) -> {
            countDownLatch.countDown();
            if (null == ex) {
                log.info("b返回处理" + res);
                result.add(res);
            }
        });

        CompletableFuture<String> c = asyncService.c();
        c.whenComplete((res, ex) -> {
            countDownLatch.countDown();
            if (null == ex) {
                log.info("b返回处理" + res);
                result.add(res);
            }
        });

        try {
            countDownLatch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("异常 : {}", e.getMessage(), e);
        }
        return result;
    }
}
