package com.xc.study.util;

public class SleepUtils {

    public static void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {

        }
    }
}
