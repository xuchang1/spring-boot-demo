package com.xc.study.config;

import com.xc.study.annotation.CustomValidated;
import com.xc.study.entity.Person;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CustomValidator implements ConstraintValidator<CustomValidated, Person> {

    private String[] values;

    @Override
    public void initialize(CustomValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Person value, ConstraintValidatorContext context) {
        // 自定义业务异常处理
        log.info("触发自定义校验逻辑");
        return false;
    }
}
