package com.kazu.carp.app.validation.common;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author akifova
 * 20.09.2021
 */
public class FieldNotEmptyValidator implements ConstraintValidator<FieldNotEmpty, Object> {

    private String fieldName;

    @Override
    public void initialize(FieldNotEmpty constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        this.fieldName = constraintAnnotation.fieldName();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null) {
            return false;
        }

        List<Field> result = new ArrayList<>();

        Class<?> i = o.getClass();
        while (i != null && i != Object.class) {
            Collections.addAll(result, i.getDeclaredFields());
            i = i.getSuperclass();
        }

        var fld = result.stream().filter(f-> f.getName().equals(fieldName)).findFirst().orElse(null);
        if(fld == null) { return false; }
        fld.setAccessible(true);
        var value = fld.get(o);
        return value != null && !String.valueOf(value).isEmpty();
    }
}
