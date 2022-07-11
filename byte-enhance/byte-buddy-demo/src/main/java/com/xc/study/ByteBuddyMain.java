package com.xc.study;

import com.xc.study.agent.PersonAgent;
import com.xc.study.entity.Person;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.instrument.Instrumentation;

public class ByteBuddyMain {

    private static final Instrumentation inst = ByteBuddyAgent.install();

    public static void main(String[] args) {
        PersonAgent.premain(null, inst);
//        PersonAgent.premainCons(null, inst);
        for (int i = 0; i < 2; i++) {
            Person person = new Person("xc" + i, i);
            System.out.println(person.say());
        }
        for (int i = 2; i < 4; i++) {
            Person person = new Person();
            System.out.println(person.say());
        }

    }
}
