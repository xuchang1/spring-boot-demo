package com.xc.study;

import com.xc.study.config.PersonAgentInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ByteBuddySpringBootMain {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ByteBuddySpringBootMain.class);
        application.addListeners(new PersonAgentInit());
        application.run(args);
    }
}
