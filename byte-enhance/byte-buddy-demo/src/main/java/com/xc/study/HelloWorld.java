package com.xc.study;

import com.xc.study.democlass.Foo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;

import java.lang.reflect.Method;

/**
 * 基于byte-buddy，在运行时动态生成一个类，拦截指定方法。
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
        testRedefine1();
    }

    private static void testSubClass() throws Exception {
        // 继承类Object，重新生成类一个类
        Class<?> dynamicType = new ByteBuddy()
                // 指定对应的父类
                .subclass(Object.class)
                // method 指定拦截方法，ElementMatchers 指定拦截的具体逻辑，此处表示拦截 toString 方法
                .method(ElementMatchers.named("toString"))
                // 拦截的逻辑，此处是返回固定值。可以自定义复杂实现。
                .intercept(FixedValue.value("Hello World!"))
                .make()
                // 通过哪个类加载器进行加载
                .load(HelloWorld.class.getClassLoader())
                .getLoaded();

        // 示例一个生成的类对象
        System.out.println(dynamicType.newInstance());
    }

    private static void testRedefine1() throws Exception {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        DynamicType.Unloaded<Object> unloaded = new ByteBuddy()
                .redefine(typePool.describe("com.xc.study.democlass.Foo").resolve(), ClassFileLocator.ForClassLoader.ofSystemLoader())
                // 如果用ClassLoadingStrategy.Default.WRAPPER，那必须为新类指定一个名字，否则在相同ClassLoader中名字冲突
                // ClassLoadingStrategy.Default.CHILD_FIRST，name定义可以省略
//                .name("RedefineFoo")
                .defineField("testField", String.class)
                .method(ElementMatchers.named("bar"))
                .intercept(FixedValue.value("Hello World!"))
                .make();
        Object demoService = unloaded.load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.CHILD_FIRST)
                .getLoaded().newInstance();
        Object o = demoService.getClass()
                .getMethod("bar")
                .invoke(demoService);
        Object o2 = demoService.getClass()
                .getMethod("bar2")
                .invoke(demoService);
        System.out.println(o.toString());
        System.out.println(o2.toString());
        System.out.println(demoService.getClass().getDeclaredField("testField"));
        // 原始的类信息并没有改变
        Foo foo = new Foo();
        System.out.println(foo.bar());
    }


    private static void testRedefine2() throws Exception {
        Class dynamicType = new ByteBuddy()
                .redefine(Foo.class)
                // 此处需要指定 redefine 类的 name，不然会和原类一样，然后抛出 Foo 类已被 load 的异常。
                .name("RedefineFoo")
                // 添加一个属性
                .defineField("testField", String.class)
                // 拦截方法
                .method(ElementMatchers.named("bar"))
                .intercept(FixedValue.value("Hello World!"))
                .make().load(HelloWorld.class.getClassLoader())
                .getLoaded();
        Object o = dynamicType.newInstance();
        Method method = o.getClass().getMethod("bar");
        System.out.println(method.invoke(o));
    }

}
