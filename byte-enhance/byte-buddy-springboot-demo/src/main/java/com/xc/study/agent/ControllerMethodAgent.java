package com.xc.study.agent;

import com.xc.study.controller.HelloController;
import com.xc.study.interceptor.MethodInvokeDetailInterceptor;
import com.xc.study.interceptor.PersonConsInterceptor;
import com.xc.study.interceptor.PersonSayInterceptor;
import com.xc.study.listener.AgentListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class ControllerMethodAgent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        // 1、拦截 HelloController 类的所有方法
//        interceptClass();

        // 2、拦截 com.xc.study.controller 包中所有方法
//        interceptPackage(instrumentation);

        // 3、拦截接口的实现
        new AgentBuilder.Default()
//                .with(new AgentListener())
                // 这个地方，最好做下限制，而不是拦截所有。该示例中拦截 service 层的实现类包
//                .type(ElementMatchers.any())
                .type(ElementMatchers.nameStartsWith("com.xc.study.service.impl"))
                .transform((builder, typeDescription, classLoader, module) ->
                                // 拦截 service 包中所有以 Service 为后缀的重写方法的类
                                builder.method(isOverriddenFrom(ElementMatchers.nameMatches("com.xc.study.service.*Service")))
                                        .intercept(Advice.to(MethodInvokeDetailInterceptor.class))
                )
                .installOn(instrumentation);
    }

    private static void interceptPackage(Instrumentation instrumentation) {
        new AgentBuilder.Default()
//                .with(new AgentListener())
                // 定义需要拦截的类,以该内容开头，即在controller包下的所有类
                .type(ElementMatchers.nameStartsWith("com.xc.study.controller"))
                .transform((builder, typeDescription, classLoader, module) ->
                                // 拦截包下的所有方法
                                builder.method(any())
                                        .intercept(Advice.to(MethodInvokeDetailInterceptor.class))

                        // 如果类还未加载，不能使用 ClassReloadingStrategy。
//                        (DynamicType.Builder<?>) builder.method(any())
//                                .intercept(Advice.to(MethodInvokeDetailInterceptor.class))
//                                .make()
//                                .load(classLoader, ClassReloadingStrategy.fromInstalledAgent())
                )
                // 装载
                .installOn(instrumentation);
    }

    private static void interceptClass() {
        new ByteBuddy().redefine(HelloController.class)
                .visit(Advice.to(MethodInvokeDetailInterceptor.class).on(ElementMatchers.any()))
                .make()
                .load(ControllerMethodAgent.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }
}
