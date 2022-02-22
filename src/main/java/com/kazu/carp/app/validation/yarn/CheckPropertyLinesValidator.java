package com.kazu.carp.app.validation.yarn;

import com.kazu.carp.yarn.definition.dto.YarnDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author akifova
 * 25.09.2021
 */
public class CheckPropertyLinesValidator implements ConstraintValidator<CheckPropertyLines, YarnDto> {
    private static final Logger logger = LoggerFactory.getLogger(CheckPropertyLinesValidator.class);

    @Override
    public void initialize(CheckPropertyLines constraintAnnotation) {
        org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public boolean isValid(YarnDto yarnDto, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}
