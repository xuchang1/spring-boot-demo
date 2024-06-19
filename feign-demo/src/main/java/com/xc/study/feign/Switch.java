package com.xc.study.feign;

public class Switch implements HelloFeignClient{

    @Autowired
    private HelloFeignClient2 helloFeignClient2;

    @Override
    public Person get() {
        return null;
    }
}
