package com.xc.study.interceptor;

import net.bytebuddy.asm.Advice;

public class PersonConsInterceptor {

    /**
     * 1、@Advice.Argument(0) : 无效，因为存在无参构造函数，无法识别索引0处参数。去掉无参构造函数后正常运行。
     * 2、@Advice.FieldValue : 在 OnMethodExit 中才能生效。OnMethodEnter 逻辑中失效。
     * 3、@Advice.This : 同样，OnMethodExit 中生效。
     */
    @Advice.OnMethodExit
//    static void enter(@Advice.AllArguments Object[] args) {
//        for (Object o : args) {
//            System.out.println("PersonConsInterceptor : " + o);
//        }
//    }
    static void enter(@Advice.This Object o) {
//    static void enter(@Advice.FieldValue("name") String o) {
//    static void enter(@Advice.Argument(0) String o) {
        System.out.println("PersonConsInterceptor : " + o);
    }
}
