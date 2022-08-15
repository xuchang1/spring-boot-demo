package com.xc.study.controller;

import com.xc.study.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @GetMapping("testTransaction")
    public void testTransaction() {
        transactionService.updatePerson();
    }
}
