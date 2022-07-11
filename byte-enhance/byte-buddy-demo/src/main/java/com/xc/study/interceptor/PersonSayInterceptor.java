package com.xc.study.interceptor;

import net.bytebuddy.asm.Advice;

public class PersonSayInterceptor {

    @Advice.OnMethodEnter
    static long enter(@Advice.Origin String method) {
        System.out.println("PersonSayInterceptor enter : " + method);
        // 返回进入方法的时间，@Advice.OnMethodExit 修饰的方法可以通过 @Advice.Enter 注解拿到返回值
        long start = System.currentTimeMillis();
        return start;
    }

    /**
     * @param o         调用方法的对象
     * @param startTime enter 方法的返回值
     */
    @Advice.OnMethodExit
    static void exit(@Advice.This Object o, @Advice.Enter long startTime) {
        System.out.println("PersonSayInterceptor exit : " + o + ", cost time : " + (System.currentTimeMillis() - startTime));
    }
}
