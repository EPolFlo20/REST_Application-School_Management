package com.example.aws.service;

import java.util.List;

import com.example.aws.dto.ProfesorDTO;
import com.example.aws.dto.ProfesorUpdateDTO;
import com.example.aws.model.Profesor;

public interface ProfesorService {

    Profesor createProfesor(ProfesorDTO profesorDTO);

    Profesor updateProfesor(Long id, ProfesorUpdateDTO profesorUpdateDTO);

    void deleteProfesor(Long id);

    Profesor findById(Long id);

    List<Profesor> findAll();

}
