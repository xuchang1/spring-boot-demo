package com.xc.study.democlass;

import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.List;

public class ChangingLoggerInterceptor {
    // @Super 可通过注解属性，指定不通的构造函数及参数，进行实例化操作。
    // 通过集成父类或实现接口的方式，创建一个辅助类对象 zuper，进行方法的调用。一般可用于基于入参进行动态的调整方法调用的实现。
    // 注意此处的对象不是实际的调用对象，因此不能基于此获取实际对象的field。
    public static List<String> log(String info, @Super MemoryDatabase zuper) {
        System.out.println("Calling database");
        try {
            // 可在调用的同时，对入参进行一些处理。
            return zuper.load(info + " (logged access)");
        } finally {
            System.out.println("Returned from database");
        }
    }
}
