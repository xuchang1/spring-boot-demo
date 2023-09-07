package com.xc.study.annotation;

import com.xc.study.config.FieldValidator;
import com.xc.study.contants.FieldCheckEnum;
import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {FieldValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface FieldValidated {
    FieldCheckEnum value();

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
