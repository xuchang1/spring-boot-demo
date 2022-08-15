package com.xc.study.democlass;

public interface Forwarder<T, S> {
    T to(S target);
}
