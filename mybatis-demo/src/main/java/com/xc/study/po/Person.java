package com.xc.study.po;

import lombok.Data;

@Data
public class Person {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;
}