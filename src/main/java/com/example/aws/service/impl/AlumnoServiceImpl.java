package com.example.aws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.aws.dto.AlumnoDTO;
import com.example.aws.dto.AlumnoUpdateDTO;
import com.example.aws.exception.AlumnoException;
import com.example.aws.model.Alumno;
import com.example.aws.repositories.AlumnoRepository;
import com.example.aws.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno createAlumno(AlumnoDTO alumnoDTO) {
        Alumno newAlumno = new Alumno();
        newAlumno.setNombres(alumnoDTO.nombres());
        newAlumno.setApellidos(alumnoDTO.apellidos());
        newAlumno.setMatricula(alumnoDTO.matricula());
        if (alumnoDTO.promedio() != 0) {
            newAlumno.setPromedio(alumnoDTO.promedio());
        }
        newAlumno.setPassword(alumnoDTO.password());
        this.alumnoRepository.save(newAlumno);

        return newAlumno;
    }

    @Override
    public Alumno updateAlumno(Long id, AlumnoUpdateDTO alumnoUpdateDTO) {
        Alumno updatedAlumno = this.findAlumno(id);

        if (this.alumnoRepository.findById(id).isPresent()) {
            updatedAlumno = this.alumnoRepository.findById(id).get();
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

        return this.alumnoRepository.save(updatedAlumno);
    }

    @Override
    public void deleteAlumno(Long id) {
        this.alumnoRepository.deleteById(id);
    }

    @Override
    public Alumno findAlumno(Long id) {
        return this.alumnoRepository.findById(id)
                .orElseThrow(() -> new AlumnoException("No existe un alumno con el id " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Alumno> findAll() {
        return this.alumnoRepository.findAll();
    }

    @Override
    public String uploadFotoPerfil(Long id, MultipartFile file) {
        Alumno alumno = findAlumno(id);

        AwsS3Service awsS3Service = new AwsS3Service();

        String fotoPerfilUrl = awsS3Service.uploadFile(id, file);
        System.out.println("Foto de perfil URL: " + fotoPerfilUrl);

        alumno.setFotoPerfilUrl(fotoPerfilUrl);
        this.alumnoRepository.save(alumno);

        return fotoPerfilUrl;
    }
}
