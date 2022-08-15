package com.xc.study.democlass;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;

/**
 * byte-buddy 默认情况下会基于强类型校验（包括入参和返回值），去匹配拦截的对应方法。如Loop.loop(String value) 方法可以匹配注释的拦截实现，
 * 但是此时我们也必须为Loop.loop(int value) 方法单独写一个拦截的实现。
 *
 * 通过@RuntimeType 注解，可以关闭强类型校验，通过一个拦截方法，拦截loop的两种调用。但是此时需要注意类型转换异常。
 */
public class LoopInterceptor {

//    @RuntimeType
//    public static String intercept(String value) {
//        System.out.println("Invoked method with: " + value);
//        return value;
//    }

    @RuntimeType
    public static Object intercept(@RuntimeType Object value) {
        System.out.println("Invoked method with: " + value);
        return value;
    }
}
