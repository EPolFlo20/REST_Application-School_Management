package com.example.aws.dto;

import com.example.aws.Validations.NameValidation;
import com.example.aws.Validations.matriculaValidation;

import jakarta.validation.constraints.*;


public record AlumnoUpdateDTO(
        @NameValidation
        String nombres,
        
        @NameValidation
        String apellidos,        

        @matriculaValidation
        String matricula,
        
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "10.0", inclusive = true)
        double promedio,

        String fotoPerfilUrl,

        String password
) {
}
