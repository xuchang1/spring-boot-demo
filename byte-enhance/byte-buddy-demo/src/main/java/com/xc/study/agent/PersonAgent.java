package com.xc.study.agent;

import com.xc.study.democlass.Foo;
import com.xc.study.democlass.FooInterceptor;
import com.xc.study.entity.Person;
import com.xc.study.interceptor.PersonConsInterceptor;
import com.xc.study.interceptor.PersonSayInterceptor;
import com.xc.study.interceptor.ToStringInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.isConstructor;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class PersonAgent {

    /**
     * Advice.to 声明的拦截方法，可以使用 @Advice 相关注解，注入相关的属性。
     */
    public static void premainPerson(String arguments, Instrumentation instrumentation) {
        // 这种加载方式，如果增强的类已经被加载了，将不能生效
        new AgentBuilder.Default()
                // 定义需要拦截的类
                .type(ElementMatchers.named("com.xc.study.entity.Person"))
                // 拦截的逻辑
                .transform((builder, typeDescription, classLoader, module) ->
                        // 拦截方法 say， 并通过 PersonSayInterceptor 执行拦截逻辑
                        (DynamicType.Builder<?>) builder.method(named("say"))
                                .intercept(Advice.to(PersonSayInterceptor.class))
                                // 拦截所有构造函数，并通过 PersonConsInterceptor 执行拦截逻辑
                                .constructor(ElementMatchers.any())
                                .intercept(Advice.to(PersonConsInterceptor.class))
                                .make()
                                .load(classLoader, ClassReloadingStrategy.fromInstalledAgent())
                )
                // 装载
                .installOn(instrumentation);
    }

    // 基于java热加载，实现对已加载过的类进行增强并重新加载到jvm中。此处增强的是用户类
    public static void premainHotSwap() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Person.class)
                .visit(Advice.to(PersonSayInterceptor.class).on(named("say")))
                .visit(Advice.to(PersonConsInterceptor.class).on(isConstructor()))
                .make()
                .load(PersonAgent.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    public static void premainHotSwapObject() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Object.class)
                .visit(Advice.to(ToStringInterceptor.class).on(named("toString")))
                .make()
                // 双亲委派
                .load(PersonAgent.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
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
