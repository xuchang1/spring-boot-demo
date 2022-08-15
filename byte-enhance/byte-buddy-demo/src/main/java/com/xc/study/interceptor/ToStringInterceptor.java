package com.xc.study.interceptor;

import net.bytebuddy.asm.Advice;

public class ToStringInterceptor {
    @Advice.OnMethodEnter
    static void enter() {
        System.out.println("ToStringInterceptor enter");
    }
}
