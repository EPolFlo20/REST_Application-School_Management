package com.example.aws.dto;

public record ProfesorDTO(
        Long id,
        String nombres,
        String apellidos,
        int numeroEmpleado,
        double horasClase) {
}
