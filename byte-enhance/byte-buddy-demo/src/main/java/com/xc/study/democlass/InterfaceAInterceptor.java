package com.xc.study.democlass;

import net.bytebuddy.asm.Advice;

public class InterfaceAInterceptor {

    @Advice.OnMethodEnter
    public static void doBefore() {
        System.out.println("InterfaceAInterceptor doBefore");
    }
}
