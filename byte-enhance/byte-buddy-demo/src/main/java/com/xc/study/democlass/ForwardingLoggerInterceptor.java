package com.xc.study.democlass;

import net.bytebuddy.implementation.bind.annotation.Pipe;

import java.util.List;
import java.util.function.Function;

public class ForwardingLoggerInterceptor {

    private final MemoryDatabase memoryDatabase;

    public ForwardingLoggerInterceptor(MemoryDatabase memoryDatabase) {
        this.memoryDatabase = memoryDatabase;
    }

//    public List<String> log(@Pipe Forwarder<List<String>, MemoryDatabase> pipe) {
//        System.out.println("Calling database");
//        try {
//            return pipe.to(memoryDatabase);
//        } finally {
//            System.out.println("Returned from database");
//        }
//    }

    // java8以上版本，可直接使用Function而不用自定义接口
    public List<String> log(@Pipe Function<MemoryDatabase, List<String>> function) {
        System.out.println("Calling database");
        try {
            // 通过入参执行被拦截的方法，并返回。
            return function.apply(memoryDatabase);
        } finally {
            System.out.println("Returned from database");
        }
    }
}
