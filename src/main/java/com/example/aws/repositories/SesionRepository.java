package com.example.aws.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aws.model.Session;

public interface SesionRepository extends JpaRepository<Session, String> {
    Optional<Session> findByAlumnoId(Long alumnoId);
}