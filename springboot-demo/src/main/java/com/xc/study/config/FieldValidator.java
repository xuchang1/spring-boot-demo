package com.xc.study.config;

import com.xc.study.annotation.FieldValidated;
import com.xc.study.contants.FieldCheckEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 基于枚举类型实现自定义参数校验
 */
@Slf4j
public class FieldValidator implements ConstraintValidator<FieldValidated, String> {

    private FieldCheckEnum fieldCheckEnum;

    private String message;

    @Override
    public void initialize(FieldValidated fieldValidated) {
        this.fieldCheckEnum = fieldValidated.value();
        this.message = StringUtils.isEmpty(fieldValidated.message())?fieldCheckEnum.getMessage():fieldValidated.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == fieldCheckEnum) {
            return false;
        }
        boolean result = false;
        switch (fieldCheckEnum) {
            case AGE:
                result = !StringUtils.isEmpty(value);
                break;
            case NAME:
                result = !StringUtils.isEmpty(value);
                break;
            default:
                break;
        }
        if (!result) {
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation().disableDefaultConstraintViolation();
        }
        return result;
    }
}
