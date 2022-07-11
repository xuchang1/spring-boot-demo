package com.xc.study.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Person {

//    @NotBlank(message = "id不能为空")
    private String id;

    private String name;

    private Integer age;
}
