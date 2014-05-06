package com.hs.whocan.service;

import com.hs.whocan.exception.TranslatedException;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * User: fish
 */
public class ValidatorException extends TranslatedException {

    private Set<ConstraintViolation<Object>> violations;

    public ValidatorException(Set<ConstraintViolation<Object>> violations) {
        this.violations = violations;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        for (ConstraintViolation<Object> violation : violations) {
            sb.append(violation.getMessage()).append(", ");
        }
        return sb.toString();
    }
}
