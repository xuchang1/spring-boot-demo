package com.xc.study.interceptor;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

public class MethodInvokeDetailInterceptor {

    public static String outFormat = "方法 : %s 执行耗时 ：%d，入参 ：%s，返回值 ：%s";

    @Advice.OnMethodEnter
    public static long doBefore() {
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    public static void doAfter(@Advice.Origin("#m") String method,
                               @Advice.AllArguments Object[] args,
                               @Advice.Return(typing = Assigner.Typing.DYNAMIC) Object result ,
                               @Advice.Enter long startTime) {
        StringBuilder param = new StringBuilder();
        for (Object arg : args) {
            param.append(arg).append(";");
        }
        System.out.println(String.format(outFormat, method, System.currentTimeMillis() - startTime, param, result));
    }
}
