# 1、ByteBuddyMain : 
正常的jar包集成方式，在启动类的main方法中，执行字节码增强逻辑，拦截生效。
PersonAgent、StudentAgent中定义拦截的方法及对应的拦截器。interceptor包中定义拦截器逻辑，包括方法enter、exit时注解的使用，
可以通过不同的注解注入方法执行生命周期中的各种信息，包含调用对象、入参、出参等。注意在agent中通过Advice.to去定义对应的拦截器时，
在interceptor中才能通过@Adice注解注入对应的信息。

# 2、HelloWorld
简单示例。基于byte-buddy在运行时，动态生成一个新的类。