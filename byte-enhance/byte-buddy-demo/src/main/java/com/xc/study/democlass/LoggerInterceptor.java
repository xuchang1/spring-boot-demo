package com.xc.study.democlass;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.List;
import java.util.concurrent.Callable;

public class LoggerInterceptor {
    public static List<String> log(@SuperCall Callable<List<String>> zuper)
            throws Exception {
        System.out.println("Calling database");
        try {
            // 调用原始类被拦截的方法。被封装成了Callable对象调用。
            return zuper.call();
        } finally {
            System.out.println("Returned from database");
        }
    }

}
