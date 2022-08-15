package com.xc.study.democlass;

import net.bytebuddy.implementation.bind.annotation.DefaultCall;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class DefaultCallTestInterceptor {

    public static String intercept(@DefaultCall Callable<String> callable) throws Exception {
        System.out.println("DefaultCallTestInterceptor intercept");
            return callable.call();
    }
}
