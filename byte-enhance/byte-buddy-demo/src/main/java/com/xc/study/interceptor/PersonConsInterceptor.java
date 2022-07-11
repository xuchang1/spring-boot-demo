package com.xc.study.interceptor;

import net.bytebuddy.asm.Advice;

public class PersonConsInterceptor {

    @Advice.OnMethodEnter
    static void enter(@Advice.AllArguments Object[] o) {
        System.out.println("PersonConsInterceptor : " + o.length + " name : " + "name");
    }

}
