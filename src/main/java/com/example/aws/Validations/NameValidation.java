package com.example.aws.Validations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NameValidationValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidation {
    String message() default "Debe ser una cadena que solo contenga letras y espacios.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
