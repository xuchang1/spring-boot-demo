package com.xc.study.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<String> a();

    CompletableFuture<String> b();

    CompletableFuture<String> c();

    String d(String res);
}
