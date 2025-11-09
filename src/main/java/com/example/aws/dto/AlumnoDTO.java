package com.example.aws.dto;

import com.example.aws.Validations.NameValidation;
import com.example.aws.Validations.idValidation;
import com.example.aws.Validations.matriculaValidation;

import jakarta.validation.constraints.*;


public record AlumnoDTO(
        @NotNull
        @idValidation
        Long id,

        @NotNull
        @NameValidation
        String nombres,
        
        @NotNull
        @NameValidation
        String apellidos,        

        @NotNull
        @matriculaValidation
        String matricula,
        
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "10.0", inclusive = true)
        double promedio) {
}
