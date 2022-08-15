package com.xc.study.config;

import com.xc.study.agent.PersonAgent;
import com.xc.study.entity.Person;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

//@Component
public class PersonAgentInit implements ApplicationListener<ApplicationStartingEvent> {

//    static {
//        1、PersonAgent.premainPerson(null, ByteBuddyAgent.install());
//    }

    @PostConstruct
    public void init() {
        //2、要在Person类初始化之前加载增强逻辑
//        PersonAgent.premainPerson(null, ByteBuddyAgent.install());
        Person person = new Person();
    }

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        // 3、监听springboot启动初期的时间
        PersonAgent.premainPerson(null, ByteBuddyAgent.install());
    }
}
