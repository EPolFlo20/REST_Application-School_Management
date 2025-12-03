package com.example.aws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.aws.dto.ProfesorDTO;
import com.example.aws.dto.ProfesorUpdateDTO;
import com.example.aws.exception.ProfesorException;
import com.example.aws.model.Profesor;
import com.example.aws.repositories.ProfesorRepository;
import com.example.aws.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public Profesor createProfesor(ProfesorDTO profesorDTO) {
        Profesor newProfesor = new Profesor();

        newProfesor.setNombres(profesorDTO.nombres());
        newProfesor.setApellidos(profesorDTO.apellidos());
        newProfesor.setNumeroEmpleado(profesorDTO.numeroEmpleado());
        newProfesor.setHorasClase(profesorDTO.horasClase());
        this.profesorRepository.save(newProfesor);

        return newProfesor;
    }

    @Override
    public Profesor updateProfesor(Long id, ProfesorUpdateDTO profesorUpdateDTO) {
        Profesor updatedProfesor = this.findById(id);

        if (profesorUpdateDTO.nombres() != null) {
            updatedProfesor.setNombres(profesorUpdateDTO.nombres());
        }

        if (profesorUpdateDTO.apellidos() != null) {
            updatedProfesor.setApellidos(profesorUpdateDTO.apellidos());
        }

        if (profesorUpdateDTO.numeroEmpleado() != updatedProfesor.getNumeroEmpleado()
                && profesorUpdateDTO.numeroEmpleado() != 0) {
            updatedProfesor.setNumeroEmpleado(profesorUpdateDTO.numeroEmpleado());
        }

        if (profesorUpdateDTO.horasClase() != updatedProfesor.getHorasClase() && profesorUpdateDTO.horasClase() != 0) {
            updatedProfesor.setHorasClase(profesorUpdateDTO.horasClase());
        }

        return this.profesorRepository.save(updatedProfesor);
    }

    @Override
    public void deleteProfesor(Long id) {
        this.profesorRepository.deleteById(id);
    }

    @Override
    public Profesor findById(Long id) {
        return this.profesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorException("Profesor no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Profesor> findAll() {
        return this.profesorRepository.findAll();
    }

}
