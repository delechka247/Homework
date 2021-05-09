package ru.kpfu.itis.transportsem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.length() > 7 && value.length() < 50
                && value.matches("[A-Za-z0-9\\._-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+");

    }
}