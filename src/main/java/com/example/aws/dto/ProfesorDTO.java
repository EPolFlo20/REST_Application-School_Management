package com.example.aws.dto;

import com.example.aws.Validations.HorasClaseValidation;
import com.example.aws.Validations.NameValidation;
import com.example.aws.Validations.NumeroEmpleadoValidation;

import jakarta.validation.constraints.NotNull;

public record ProfesorDTO(
        @NotNull
        @NameValidation        
        String nombres,
        
        @NotNull
        @NameValidation
        String apellidos,
        
        @NotNull
        @NumeroEmpleadoValidation
        int numeroEmpleado,
        
        @NotNull
        @HorasClaseValidation
        int horasClase) {
}
