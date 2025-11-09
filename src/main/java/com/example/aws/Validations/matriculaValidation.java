package com.example.aws.Validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = MatriculaValidationValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface matriculaValidation {
    String message() default "La matrícula debe ser tener el formato A[Números].";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
