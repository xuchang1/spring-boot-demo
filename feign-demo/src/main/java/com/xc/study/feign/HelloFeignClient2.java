package com.xc.study.feign;

import com.xc.study.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@FeignClient(name = "hello", url = "http://localhost:8080")
public interface HelloFeignClient2 {

    @GetMapping("get")
    Person get();
}
