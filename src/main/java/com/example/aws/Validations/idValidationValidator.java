package com.example.aws.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class idValidationValidator implements ConstraintValidator<idValidation, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            long number = Long.parseLong(value.toString());
            if (number <= 0) {
                setCustomMessage(context, "Debe ser un número entero positivo.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            setCustomMessage(context, "Debe ser un número entero válido.");
            return false;
        }
    }

    private void setCustomMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
