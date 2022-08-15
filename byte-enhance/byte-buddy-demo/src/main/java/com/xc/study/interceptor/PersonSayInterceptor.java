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
     * 1、@Advice.This : 写入调用当前方法的对象。可判断转型后，调用相关方法或获取属性值。
     * 2、@Advice.FieldValue : 通过该注解，注入特定属性值。
     * 3、@Advice.Return : 通过该注解，注入返回值。
     * 4、@Advice.Argument : 注入入参，value填参数索引。
     * @param o         调用方法的对象
     * @param startTime enter 方法的返回值
     */
    @Advice.OnMethodExit
    static void exit(@Advice.Argument(0) String o, @Advice.Enter long startTime) {
//    static void exit(@Advice.Return String o, @Advice.Enter long startTime) {
//    static void exit(@Advice.FieldValue("name") String o, @Advice.Enter long startTime) {
//    static void exit(@Advice.This Object o, @Advice.Enter long startTime) {
        System.out.println("PersonSayInterceptor exit : " + o + ", cost time : " + (System.currentTimeMillis() - startTime));
    }
}
