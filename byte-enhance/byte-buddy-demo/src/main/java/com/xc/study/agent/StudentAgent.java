package com.xc.study.agent;

import com.xc.study.interceptor.PersonConsInterceptor;
import com.xc.study.interceptor.PersonSayInterceptor;
import com.xc.study.interceptor.StudentConsInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class StudentAgent {

    public static void premainPerson(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                // 定义需要拦截的类
                .type(ElementMatchers.named("com.xc.study.entity.Student"))
                // 拦截的逻辑
                .transform((builder, typeDescription, classLoader, module) ->
                        builder
                                .method(named("say"))
                                .intercept(Advice.to(StudentConsInterceptor.class))
                                // 拦截 Student 类的所有构造函数，并通过 StudentConsInterceptor 执行拦截逻辑
                                .constructor(ElementMatchers.any())
                                .intercept(Advice.to(StudentConsInterceptor.class))

                        //                    builder = builder.visit(Advice.to(CPoolEntryInterceptor.class).on(ElementMatchers.named("close")));
                        //                    builder = builder.visit(Advice.to(CPoolEntryInterceptor.class).on(isConstructor()));
                )
                // 装载
                .installOn(instrumentation);
    }
}
