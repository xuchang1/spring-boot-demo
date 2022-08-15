package com.xc.study.democlass;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

public class FooInterceptor {

    @Advice.OnMethodEnter
    static void intercept() {
        System.out.println("OnMethodEnter");
    }
}
