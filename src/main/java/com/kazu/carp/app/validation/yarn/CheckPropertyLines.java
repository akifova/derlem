package com.kazu.carp.app.validation.yarn;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author akifova
 * 25.09.2021
 */
@Constraint(validatedBy = CheckPropertyLinesValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPropertyLines {
    String message() default "{irregular.archive.userFiles}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
