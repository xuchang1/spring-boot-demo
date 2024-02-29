package com.xc.study.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.xc.study.annotation.RateLimitPerSec;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Aspect
@Component
@Slf4j
public class RateLimitAspect {

    @Pointcut("@annotation(rateLimitPerSec)")
    public void rateLimitPointCut(RateLimitPerSec rateLimitPerSec) {}

    private volatile Map<String, RateLimiter> rateMap = new HashMap<>();

    @Resource(name = "requestThirdPartyThreadPool")
    private Executor requestThirdPartyThreadPool;

    @Bean(name = "requestThirdPartyThreadPool")
    public Executor batchThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(8);
        threadPoolTaskExecutor.setMaxPoolSize(8);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.setThreadNamePrefix("request-third-party-thread-pool-");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    @Around("rateLimitPointCut(rateLimitPerSec)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, RateLimitPerSec rateLimitPerSec) throws Throwable {
        Signature signature = joinPoint.getSignature();
        RateLimiter rateLimiter = getRateLimiter(rateLimitPerSec, signature);

        rateLimiter.acquire();
        // 同步调用
        if (rateLimitPerSec.isSync()) {
           return joinPoint.proceed();
        }

        // batch异步调用时，业务逻辑需要在方法内部处理，限制无返回值
        MethodSignature methodSignature = (MethodSignature) signature;
        if (!methodSignature.getReturnType().equals(Void.TYPE)) {
            throw new Exception("批量异步调用，方法不支持返回数据");
        }

        // 异步调用
        requestThirdPartyThreadPool.execute(() -> {
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                log.error("方法执行异常");
            }
        });
        return null;
    }

    private RateLimiter getRateLimiter(RateLimitPerSec rateLimitPerSec, Signature signature) {
        String rateKey = signature.getDeclaringTypeName() + signature.getName();
        RateLimiter rateLimiter = rateMap.get(rateKey);
        if (null == rateLimiter) {
            synchronized (RateLimitAspect.class) {
                rateLimiter = rateMap.get(rateKey);
                if (null == rateLimiter) {
                    rateLimiter = RateLimiter.create(rateLimitPerSec.requestPerSec(), 2, TimeUnit.SECONDS);
                    Map<String, RateLimiter> newRateMap = new HashMap<>(rateMap);
                    newRateMap.put(rateKey, rateLimiter);
                    rateMap = newRateMap;
                }
            }
        }
        return rateLimiter;
    }
}
