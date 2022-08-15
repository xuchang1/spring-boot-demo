package com.xc.study.democlass;

public class ThisTest {
    private String name;

    public String getName() {
        System.out.println("ThisTest getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("ThisTest setName");
        this.name = name;
    }
}
