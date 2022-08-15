package com.xc.study;

import com.xc.study.democlass.Foo;
import com.xc.study.democlass.FooInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class HotSwapLoad {
    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws Exception {
        Foo foo = new Foo();
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Foo.class)
                .visit(Advice.to(FooInterceptor.class).on(ElementMatchers.nameStartsWith("foo")))
//                .name("com.xc.study.democlass.Foo")
                .make()
                // java 的热加载机制
                .load(HelloWorld.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(foo.bar2());
        System.out.println(foo.foo(1));
    }
}
