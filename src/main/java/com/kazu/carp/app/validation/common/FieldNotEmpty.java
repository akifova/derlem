package com.kazu.carp.app.validation.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author akifova
 * 20.09.2021
 */
@Constraint(validatedBy = FieldNotEmptyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldNotEmpty {
    String fieldName() default "id";

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
