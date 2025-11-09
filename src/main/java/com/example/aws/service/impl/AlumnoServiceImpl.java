package com.example.aws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.aws.dto.AlumnoDTO;
import com.example.aws.dto.AlumnoUpdateDTO;
import com.example.aws.exception.AlumnoException;
import com.example.aws.model.Alumno;
import com.example.aws.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final List<Alumno> alumnos = new ArrayList<>();

    @Override
    public Alumno createAlumno(AlumnoDTO alumnoDTO) {
        validateAlumnoDoesNotExist(alumnoDTO.id());

        Alumno newAlumno = new Alumno(alumnoDTO.id(), alumnoDTO.nombres(), alumnoDTO.apellidos(), alumnoDTO.matricula(),
                alumnoDTO.promedio());
        alumnos.add(newAlumno);

        return newAlumno;
    }

    @Override
    public Alumno updateAlumno(Long id, AlumnoUpdateDTO alumnoUpdateDTO) {
        Alumno updatedAlumno = this.findAlumno(id);

        if (alumnoUpdateDTO.id() != null) {
            updatedAlumno.setId(alumnoUpdateDTO.id());
        }

        if (alumnoUpdateDTO.nombres() != null) {
            updatedAlumno.setNombres(alumnoUpdateDTO.nombres());
        }

        if (alumnoUpdateDTO.apellidos() != null) {
            updatedAlumno.setApellidos(alumnoUpdateDTO.apellidos());
        }

        if (alumnoUpdateDTO.matricula() != null) {
            updatedAlumno.setMatricula(alumnoUpdateDTO.matricula());
        }

        if (alumnoUpdateDTO.promedio() != updatedAlumno.getPromedio() && alumnoUpdateDTO.promedio() != 0) {
            updatedAlumno.setPromedio(alumnoUpdateDTO.promedio());
        }

        int index = alumnos.indexOf(updatedAlumno);
        alumnos.set(index, updatedAlumno);

        return updatedAlumno;
    }

    @Override
    public void deleteAlumno(Long id) {
        Alumno alumno = this.findAlumno(id);
        alumnos.remove(alumno);
    }

    @Override
    public Optional<Alumno> findById(Long id) {
        return alumnos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public Alumno findAlumno(Long id) {
        Alumno alumno = this.findById(id)
                .orElseThrow(() -> new AlumnoException("No existe un alumno con el id " + id, HttpStatus.NOT_FOUND));
        return alumno;
    }

    @Override
    public List<Alumno> findAll() {
        return this.alumnos;
    }

    private void validateAlumnoDoesNotExist(Long id) {
        findById(id).ifPresent(a -> {
            throw new AlumnoException("Ya existe un alumno con el id " + id, HttpStatus.BAD_REQUEST);
        });
    }
}
