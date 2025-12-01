package com.example.aws.service;

import java.util.List;

import com.example.aws.dto.AlumnoDTO;
import com.example.aws.dto.AlumnoUpdateDTO;
import com.example.aws.model.Alumno;

public interface AlumnoService {

    Alumno createAlumno(AlumnoDTO alumnoDTO);

    Alumno updateAlumno(Long id, AlumnoUpdateDTO alumnoUpdateDTO);

    void deleteAlumno(Long id);

    Alumno findAlumno(Long id);

    List<Alumno> findAll();

    String uploadFotoPerfil(Long id);

    void sendAlumnoInfoEmail(Long id);
}
