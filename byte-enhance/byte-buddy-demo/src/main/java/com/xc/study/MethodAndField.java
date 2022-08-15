package com.xc.study;


import com.xc.study.democlass.*;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Pipe;

import java.util.function.Function;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class MethodAndField {
    public static void main(String[] args) throws Exception{
        testFieldValue();
    }

    private static void testMethod() throws Exception {
        String toString = new ByteBuddy()
                .subclass(Object.class)
                .name("example.Type")
//                .method(named("toString"))
                // 更准确的拦截，方法名称为toString，返回值类型为String，传参个数0
                .method(named("toString").and(returns(String.class)).and(takesArguments(0)))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();
        System.out.println(toString);
    }

    /**
     * 方法拦截匹配逻辑
     * 1、load 拦截规则时，使用stack存储规则，先入后出。
     * 2、匹配规则时，从stack中拉取规则匹配，满足第一个匹配的拦截规则即返回。
     */
    private static void testMethodMatch() throws Exception {
        Foo foo = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class)).intercept(FixedValue.value("One!"))
                .method(named("foo")).intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1))).intercept(FixedValue.value("Three!"))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        System.out.println(foo.bar()); // One!
        System.out.println(foo.foo()); // Two!
        System.out.println(foo.foo("1")); // Three!
    }

    private static void testDelegatingMethod() throws Exception {
        String str = new ByteBuddy()
                .subclass(Source.class)
                // 拦截 hello 方法，并把方法的执行委派给Target类。（注意此处的逻辑，方法不一定需要同名，有指定的匹配逻辑优先级规则在里面）
                // 拦截的方法中，没加注解，默认会加上 @Argument 注解
                .method(named("hello")).intercept(MethodDelegation.to(Target.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .hello("World");
        System.out.println(str);
    }

    private static void testSuperCall() throws Exception {
        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                // @SuperCall 注解，通过 Callable 封装了原方法的执行逻辑，可以在执行前后执行对应的拦截操作。
                // 如果没有返回值，也可以通过 Runnable 封装
                // 可以通过相关注解，注入方法调用的对象、参数、方法签名等各种信息。具体注解，可看下 @SuperCall 所在的包。
                .method(named("load")).intercept(MethodDelegation.to(LoggerInterceptor.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        loggingDatabase.load("test").forEach(System.out::println);
    }

    private static void testSuper() throws Exception {
        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                // @SuperCall 注解，通过 Callable 封装了原方法的执行逻辑，可以在执行前后执行对应的拦截操作。
                // 如果没有返回值，也可以通过 Runnable 封装
                // 可以通过相关注解，注入方法调用的对象、参数、方法签名等各种信息。具体注解，可看下 @SuperCall 所在的包。
                .method(named("load")).intercept(MethodDelegation.to(ChangingLoggerInterceptor.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        // debug 可以发现，loggingDatabase 这个对象和 @Super 修饰的对象，不是同一个对象。后者是基于注解属性动态生成的一个对象，不能由此获取field值。
        // 多次调用发现，@Super 修饰的对象，本身是不会发生变化的。
        loggingDatabase.load("test").forEach(System.out::println);
        loggingDatabase.load("test").forEach(System.out::println);
    }

    private static void testRuntimeType() throws Exception {
        Loop loop = new ByteBuddy()
                .subclass(Loop.class)
                .method(named("loop")).intercept(MethodDelegation.to(LoopInterceptor.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        System.out.println(loop.loop(1));
        System.out.println(loop.loop("2"));
    }

    private static void testPipe() throws Exception {
        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                .method(named("load"))
                .intercept(MethodDelegation.withDefaultConfiguration()
//                        .withBinders(Pipe.Binder.install(Forwarder.class))
                        .withBinders(Pipe.Binder.install(Function.class))
                        // 基于一个已存在的实例，进行方法的拦截处理
                        // 此处new MemoryDatabase()可表示为一个已存在的实例入参，通过该实例去调用方法。@Super这种的是动态构建的。
                        // 而且注意到，此处的拦截方法不是static类级别的，而是实例级别的。
                        .to(new ForwardingLoggerInterceptor(new MemoryDatabase())))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        System.out.println(loggingDatabase.load("1"));
    }
    private static void testThis() throws Exception {
        ThisTest thisTest = new ByteBuddy()
                .subclass(ThisTest.class)
                .method(named("setName")).intercept(MethodDelegation.to(ThisTestInterceptor.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        thisTest.setName("xu");
    }

    /**
     * 1、@DefaultCall : 拦截时，可以封装接口中default方法的调用，此时和@SuperCall的使用没啥区别。
     *      拦截正常类方法时，不能使用该注解，只能用@SuperCall。
     */
    private static void testDefaultCall() throws Exception {
        DefaultCallTest defaultCallTest = new ByteBuddy()
                .subclass(DefaultCallTest.class)
                .method(named("doSomething")).intercept(MethodDelegation.to(DefaultCallTestInterceptor.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        System.out.println(defaultCallTest.doSomething());
    }

    /**
     * 1、@FieldValue ：注入一个属性值。访问权限是个坑。
     */
    private static void testFieldValue() throws Exception {
        FieldValueTest fieldValueTest = new ByteBuddy()
                .subclass(FieldValueTest.class)
                .method(named("doSomething")).intercept(MethodDelegation.to(FieldValueTestInterceptor.class))
                .make()
                .load(MethodAndField.class.getClassLoader())
                .getLoaded()
                .newInstance();
        fieldValueTest.setName("xu");
        System.out.println(fieldValueTest.doSomething());
    }
}

