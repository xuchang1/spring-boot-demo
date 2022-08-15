package com.xc.study;

import com.xc.study.agent.InterfaceAAgent;
import com.xc.study.democlass.InterfaceA;
import com.xc.study.democlass.InterfaceAImplA;
import com.xc.study.democlass.InterfaceAImplB;

public class InterfaceIntercept {
    public static void main(String[] args) {
        InterfaceAAgent.premain();
        InterfaceA interfaceAImplA = new InterfaceAImplA();
        InterfaceA interfaceAImplB = new InterfaceAImplB();
        System.out.println(interfaceAImplA.doSomething());
        System.out.println(interfaceAImplB.doSomething());
    }
}
