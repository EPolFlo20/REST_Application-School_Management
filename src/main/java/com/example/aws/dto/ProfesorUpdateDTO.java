package com.example.aws.dto;

import com.example.aws.Validations.HorasClaseValidation;
import com.example.aws.Validations.NameValidation;
import com.example.aws.Validations.NumeroEmpleadoValidation;
import com.example.aws.Validations.idValidation;

public record ProfesorUpdateDTO(
        @idValidation
        Long id,

        @NameValidation        
        String nombres,
        
        @NameValidation
        String apellidos,
        
        @NumeroEmpleadoValidation
        int numeroEmpleado,
        
        @HorasClaseValidation
        int horasClase) {
}
