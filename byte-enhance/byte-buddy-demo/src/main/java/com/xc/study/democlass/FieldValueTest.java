package com.xc.study.democlass;

public class FieldValueTest {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String doSomething() {
        System.out.println("doSomething");
        return name + "1";
    }
}
