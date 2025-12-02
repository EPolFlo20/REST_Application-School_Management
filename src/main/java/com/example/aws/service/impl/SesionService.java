package com.example.aws.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.aws.components.SessionGenerator;
import com.example.aws.exception.AlumnoException;
import com.example.aws.repositories.SesionRepository;
import com.example.aws.service.AlumnoService;
import com.example.aws.model.Alumno;
import com.example.aws.model.Session;

@Service
public class SesionService {
    private final SesionRepository sesionRepository;
    private final AlumnoService alumnoService;

    public SesionService(SesionRepository sesionRepository, AlumnoService alumnoService) {
        this.sesionRepository = sesionRepository;
        this.alumnoService = alumnoService;
    }

    public Session crearSesion(Long alumnoId, String password) {

        // Verifica si el alumno existe
        Alumno alumno = alumnoService.findAlumno(alumnoId);

        // Verifica si la contraseña es correcta
        if (!alumno.getPassword().equals(password)) {
            throw new AlumnoException("Credenciales inválidas", HttpStatus.BAD_REQUEST);
        }

        // Buscando una sesión activa
        Session session = sesionRepository.findByAlumnoId(alumno.getId()).orElse(null);

        long fecha = System.currentTimeMillis();
        String token = SessionGenerator.generateSessionString();

        // Si no hay una sesión activa, crea una nueva
        if (session == null) {
            session = new Session();
            String id = UUID.randomUUID().toString();
            session.setId(id);
        }

        // Actualiza los detalles de la sesión
        session.setFecha(fecha);
        session.setAlumnoId(alumnoId);
        session.setActive(true);
        session.setSessionString(token);

        return sesionRepository.save(session);
    }

    public boolean verify(Long alumnoId, String sessionString) {
        Alumno alumno = alumnoService.findAlumno(alumnoId);
        Session session = sesionRepository.findByAlumnoId(alumno.getId()).orElse(null);
        if (session == null) {
            return false;
        }

        Boolean isSameSessionString = session.getSessionString().equals(sessionString);

        if (isSameSessionString && session.isActive()) {
            return session.isActive();
        } else {
            throw new AlumnoException("SessionString incorrecto o caducado", HttpStatus.BAD_REQUEST);
        }
    }

    public void cerrarSesion(Long alumnoId, String sessionString) {
        Alumno alumno = alumnoService.findAlumno(alumnoId);

        Session session = sesionRepository.findByAlumnoId(alumno.getId()).orElse(null);

        if (!session.getSessionString().equals(sessionString)) {
            throw new AlumnoException("sessionString incorrecto", HttpStatus.BAD_REQUEST);
        }

        session.setActive(false);
        sesionRepository.save(session);
    }
}
