package com.xc.study.democlass;


import net.bytebuddy.implementation.bind.annotation.FieldValue;

public class FieldValueTestInterceptor {

    /**
     * 需要配置field名称，并且对 instrumented 类型来说，field必须能够访问到。必须public或者同一个包下？
     */
    public static String intercept(@FieldValue("name") String name) {
        System.out.println("do intercept");
        return name + " intercept";
    }
}
