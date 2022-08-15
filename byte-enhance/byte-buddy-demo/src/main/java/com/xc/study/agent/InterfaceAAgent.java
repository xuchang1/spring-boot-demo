package com.xc.study.agent;

import com.xc.study.democlass.InterfaceA;
import com.xc.study.democlass.InterfaceAImplA;
import com.xc.study.democlass.InterfaceAInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class InterfaceAAgent {

    public static void premain() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(InterfaceA.class)
                .visit(Advice.to(InterfaceAInterceptor.class).on(named("doSomething")))
                .make()
                .load(InterfaceAAgent.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }
}
