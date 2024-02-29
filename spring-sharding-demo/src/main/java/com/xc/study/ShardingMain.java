package com.xc.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xc.study.mapper")
public class ShardingMain {
    public static void main(String[] args) {
        SpringApplication.run(ShardingMain.class, args);
    }
}