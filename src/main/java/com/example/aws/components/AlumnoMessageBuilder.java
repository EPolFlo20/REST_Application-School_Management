package com.example.aws.components;

import com.example.aws.model.Alumno;

public class AlumnoMessageBuilder {

    public static String buildAlumnoMessage(Alumno alumno, String accion) {
        return """
                Acción: %s
                ID: %d
                Nombre: %s %s
                Matrícula: %s
                Promedio: %.2f
                """.formatted(
                accion.toUpperCase(),
                alumno.getId(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getMatricula(),
                alumno.getPromedio());
    }
}
