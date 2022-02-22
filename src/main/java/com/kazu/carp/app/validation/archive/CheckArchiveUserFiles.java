package com.kazu.carp.app.validation.archive;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author akifova
 * 23.09.2021
 */
@Constraint(validatedBy = CheckArchiveUserFilesValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckArchiveUserFiles {
    String message() default "{irregular.archive.userFiles}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
