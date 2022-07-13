# 1、ByteBuddyMain : 
正常的jar包集成方式，在启动类的main方法中，执行字节码增强逻辑，拦截生效。
PersonAgent、StudentAgent中定义拦截的方法及对应的拦截器。interceptor包中定义拦截器逻辑，包括方法enter、exit时注解的使用，
可以通过不同的注解注入方法执行生命周期中的各种信息，包含调用对象、入参、出参等。注意在agent中通过Advice.to去定义对应的拦截器时，
在interceptor中才能通过@Adice注解注入对应的信息。

# 2、ByteBuddySpringBootMain
在springboot工程中，使用byte-buddy进行字节码增强。PersonAgentInit中通过@PostConstruct注解初始化字节码增强agent的调用。
如果在调用之前，先初始化了增强的类（new了一个person对象），会发现增强失效。
所以要基于springboot启动的生命周期，使得被增强的逻辑在增强类初始化之前加载。
