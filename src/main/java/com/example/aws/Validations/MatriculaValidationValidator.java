package com.example.aws.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatriculaValidationValidator implements ConstraintValidator<matriculaValidation, String> {

    private static final String MATRICULA_REGEX = "^A\\d+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (!value.matches(MATRICULA_REGEX)) {
            this.setCustomMessage(context, "Debe tener el formato A[Números].");
            return false;
        }
        return true;
    }

    private void setCustomMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
