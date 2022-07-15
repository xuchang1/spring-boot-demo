package com.xc.study.interceptor;

import com.xc.study.entity.Person;
import net.bytebuddy.asm.Advice;

public class StudentConsInterceptor {
    /**
     * 1、@Advice.FieldValue : 可以注入对象
     */
    @Advice.OnMethodExit
    static void enter(@Advice.FieldValue("person") Person o, @Advice.Origin("#m") String method) {
        System.out.println("StudentConsInterceptor : " + o + "-" + method);
    }
}
