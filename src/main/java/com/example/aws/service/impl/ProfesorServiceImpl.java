package com.example.aws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.aws.dto.ProfesorDTO;
import com.example.aws.dto.ProfesorUpdateDTO;
import com.example.aws.exception.AlumnoException;
import com.example.aws.exception.ProfesorException;
import com.example.aws.model.Profesor;
import com.example.aws.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final List<Profesor> profesores = new ArrayList<>();

    @Override
    public Profesor createProfesor(ProfesorDTO profesorDTO) {
        validateProfesorDoesNotExist(profesorDTO.id());

        Profesor newProfesor = new Profesor(profesorDTO.id(), profesorDTO.nombres(), profesorDTO.apellidos(),
                profesorDTO.numeroEmpleado(), profesorDTO.horasClase());
        this.profesores.add(newProfesor);

        return newProfesor;
    }

    @Override
    public Profesor updateProfesor(Long id, ProfesorUpdateDTO profesorUpdateDTO) {
        Profesor updatedProfesor = this.findProfesor(id);

        if (profesorUpdateDTO.id() != null) {
            updatedProfesor.setId(profesorUpdateDTO.id());
        }

        if (profesorUpdateDTO.nombres() != null) {
            updatedProfesor.setNombres(profesorUpdateDTO.nombres());
        }

        if (profesorUpdateDTO.apellidos() != null) {
            updatedProfesor.setApellidos(profesorUpdateDTO.apellidos());
        }

        if (profesorUpdateDTO.numeroEmpleado() != updatedProfesor.getNumeroEmpleado() && profesorUpdateDTO.numeroEmpleado() != 0) {
            updatedProfesor.setNumeroEmpleado(profesorUpdateDTO.numeroEmpleado());
        }

        if (profesorUpdateDTO.horasClase() != updatedProfesor.getHorasClase() && profesorUpdateDTO.horasClase() != 0) {
            updatedProfesor.setHorasClase(profesorUpdateDTO.horasClase());
        }

        int index = profesores.indexOf(updatedProfesor);
        profesores.set(index, updatedProfesor);

        return updatedProfesor;
    }

    @Override
    public void deleteProfesor(Long id) {
        Profesor profesor = this.findProfesor(id);
        this.profesores.remove(profesor);
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        return this.profesores.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public Profesor findProfesor(Long id) {
        Profesor profesor = this.findById(id)
                .orElseThrow(() -> new ProfesorException("No existe un profesor con id: " + id, HttpStatus.NOT_FOUND));
        return profesor;
    }

    @Override
    public List<Profesor> findAll() {
        return this.profesores;
    }

    private void validateProfesorDoesNotExist(Long id) {
        findById(id).ifPresent(a -> {
            throw new AlumnoException("Ya existe un profesor con el id " + id, HttpStatus.BAD_REQUEST);
        });
    }


}
