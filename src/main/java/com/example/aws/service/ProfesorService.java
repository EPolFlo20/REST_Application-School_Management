package com.example.aws.service;

import java.util.List;
import java.util.Optional;

import com.example.aws.dto.ProfesorDTO;
import com.example.aws.dto.ProfesorUpdateDTO;
import com.example.aws.model.Profesor;

public interface ProfesorService {

    Profesor createProfesor(ProfesorDTO profesorDTO);

    Profesor updateProfesor(Long id, ProfesorUpdateDTO profesorUpdateDTO);

    void deleteProfesor(Long id);

    Optional<Profesor> findById(Long id);

    Profesor findProfesor(Long id);

    List<Profesor> findAll();

}
