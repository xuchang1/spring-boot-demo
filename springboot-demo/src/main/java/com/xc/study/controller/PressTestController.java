package com.xc.study.controller;

import com.xc.study.entity.Person;
import com.xc.study.util.SleepUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PressTestController {

    @GetMapping("cpuLoopTest")
    public void cpuLoopTest() {
        while (true){
//            System.out.println("11");
        }
    }

    @GetMapping("testTrace")
    public void testTrace() {
        this.method1();
        this.method2();
    }

    @PostMapping("testWatch")
    public Person testWatch(@RequestBody Person person) {
        Person person1 = new Person();
        person1.setId(person.getId());
        person1.setName(person.getName());
        person1.setAge(person.getAge() + 1);
        return person1;
    }

    private void method1() {
        this.method3();
        SleepUtils.sleep(1);
    }
    private void method2() {
        SleepUtils.sleep(2);
    }

    private void method3() {
        SleepUtils.sleep(3);
    }
}
