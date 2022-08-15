package com.xc.study.democlass;

import java.util.Arrays;
import java.util.List;

public class MemoryDatabase {
    public List<String> load(String info) {
        System.out.println("MemoryDatabase do load");
        return Arrays.asList(info + ": foo", info + ": bar");
    }
}
