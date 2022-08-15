package com.xc.study.democlass;

import net.bytebuddy.implementation.bind.annotation.This;

public class ThisTestInterceptor {

    /**
     * 这个地方，注入的是由byte-buddy生成的类对象
     */
    public static void intercept(@This ThisTest thisTest) {
        System.out.println("doBefore");
        // 这个地方并没有拿到数据，set方法已经被委托了，本身的逻辑并没有执行
        System.out.println(thisTest.getName());
        System.out.println("doAfter");
    }
}
