package com.xc.study;

import com.xc.study.agent.PersonAgent;
import com.xc.study.agent.StudentAgent;
import com.xc.study.entity.Person;
import com.xc.study.entity.Student;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.instrument.Instrumentation;

public class ByteBuddyMain {

    private static final Instrumentation inst = ByteBuddyAgent.install();

    public static void main(String[] args) {
//        enhancePerson();
        enhanceStudent();
    }

    public static void enhancePerson() {
        PersonAgent.premainPerson(null, inst);
        for (int i = 0; i < 2; i++) {
            Person person = new Person("xc" + i, i);
            System.out.println(person.say("prefix"));
        }
        for (int i = 2; i < 4; i++) {
            Person person = new Person();
            System.out.println(person.say("prefix"));
        }
    }

    public static void enhanceStudent() {
        StudentAgent.premainPerson(null, inst);
        for (int i = 0; i < 3; i++) {
            Person person = new Person("xc" + i, i);
            Student student = new Student(person, "三年级1班");
        }
    }
}
