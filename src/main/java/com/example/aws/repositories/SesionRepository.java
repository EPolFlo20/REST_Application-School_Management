package com.example.aws.repositories;

import java.util.Optional;

import com.example.aws.model.Session;

public interface SesionRepository{
    Optional<Session> findByAlumnoId(Long alumnoId);
    Session save(Session session);
}