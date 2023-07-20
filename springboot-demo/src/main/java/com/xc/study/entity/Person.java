package com.xc.study.entity;

import com.xc.study.annotation.FieldValidated;
import com.xc.study.contants.FieldCheckEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Person {

    @NotBlank(message = "id不能为空")
    private String id;

    @FieldValidated(FieldCheckEnum.NAME)
    private String name;

    @FieldValidated(value = FieldCheckEnum.AGE, message = "年纪")
    private String age;
}
