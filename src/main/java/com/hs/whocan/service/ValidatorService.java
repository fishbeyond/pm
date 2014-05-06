package com.hs.whocan.service;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class ValidatorService {

    public void validate(Object obj) {
        Set<ConstraintViolation<Object>> validate = getValidator().validate(obj);
        if (validate.isEmpty()) {
            return;
        }
        throw new ValidatorException(validate);
    }

    private static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}