package com.xc.study;

import com.xc.study.agent.PersonAgent;
import com.xc.study.agent.StudentAgent;
import com.xc.study.entity.Person;
import com.xc.study.entity.Student;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

public class ByteBuddyAgentMain {

    private static final Instrumentation inst = ByteBuddyAgent.install();

    public static void main(String[] args) {
//        enhancePerson();
//        enhanceStudent();
        enhanceObject();
    }

    private static void enhanceObject() {
        Person o = new Person();
        PersonAgent.premainHotSwapObject();
        System.out.println(o);
    }

    public static void enhancePerson() {
        Person person1 = new Person();
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
            student.say();
        }
    }
}
