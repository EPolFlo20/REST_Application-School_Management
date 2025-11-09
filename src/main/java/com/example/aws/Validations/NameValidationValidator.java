package com.example.aws.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidationValidator implements ConstraintValidator<NameValidation, String> {

    private static final String NAME_REGEX = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value.isBlank()) {
            this.setCustomMessage(context, "No puede estar en blanco.");
            return false;
        }

        if (!value.matches(NAME_REGEX)) {
            this.setCustomMessage(context, "Solo pueden contener letras y espacios");
            return false;
        }

        return true;
    }

    private void setCustomMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
