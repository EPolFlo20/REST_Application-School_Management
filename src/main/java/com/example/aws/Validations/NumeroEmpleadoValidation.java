package com.example.aws.Validations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NumeroEmpleadoValidationValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroEmpleadoValidation {
    String message() default "El Número de Empleado solo puede ser un entero positivo.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
