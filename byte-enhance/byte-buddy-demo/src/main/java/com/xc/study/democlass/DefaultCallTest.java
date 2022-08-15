package com.xc.study.democlass;

public interface DefaultCallTest {

    default String doSomething() {
        System.out.println("DefaultCallTest doSomething");
        return "doSomething";
    }
}
