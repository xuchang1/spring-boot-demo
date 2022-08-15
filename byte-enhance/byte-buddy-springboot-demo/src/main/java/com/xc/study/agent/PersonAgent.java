package com.xc.study.agent;

import com.xc.study.interceptor.PersonConsInterceptor;
import com.xc.study.interceptor.PersonSayInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class PersonAgent {

    /**
     * Advice.to 声明的拦截方法，可以使用 @Advice 相关注解，注入相关的属性。
     */
    public static void premainPerson(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                // 定义需要拦截的类
                .type(ElementMatchers.named("com.xc.study.entity.Person"))
                // 拦截的逻辑
                .transform((builder, typeDescription, classLoader, module) ->
                        // 拦截方法 say， 并通过 PersonSayInterceptor 执行拦截逻辑
                        builder.method(named("say"))
                                .intercept(Advice.to(PersonSayInterceptor.class))
                                // 拦截所有构造函数，并通过 PersonConsInterceptor 执行拦截逻辑
                                .constructor(ElementMatchers.any())
                                .intercept(Advice.to(PersonConsInterceptor.class))
                )
                // 装载
                .installOn(instrumentation);
    }

    public static void premainSay(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.named("com.xc.study.entity.Person"))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(named("say")).intercept(MethodDelegation.to(PersonSayInterceptor.class)))
                .installOn(instrumentation);
    }

    public static void premainCons(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.named("com.xc.study.entity.Person"))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.constructor(ElementMatchers.any()).intercept(Advice.to(PersonConsInterceptor.class))
                )
                .installOn(instrumentation);
    }
}
