package com.xc.study.democlass;

public class Target {
    public static String hello(String name) {
        return "Hello " + name + "!";
    }

    public static String intercept(String name) { return "Hello " + name + "!"; }
    public static String intercept(int i) { return Integer.toString(i); }
    public static String intercept(Object o) { return o.toString(); }
}
