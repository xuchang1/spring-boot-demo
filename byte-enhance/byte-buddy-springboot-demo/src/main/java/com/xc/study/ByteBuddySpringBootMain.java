package com.xc.study;

import com.xc.study.agent.ControllerMethodAgent;
import com.xc.study.config.PersonAgentInit;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ByteBuddySpringBootMain {

    public static void main(String[] args) {
        ControllerMethodAgent.premain(null, ByteBuddyAgent.install());
        SpringApplication application = new SpringApplication(ByteBuddySpringBootMain.class);
//        application.addListeners(new PersonAgentInit());
        application.run(args);
    }
}
