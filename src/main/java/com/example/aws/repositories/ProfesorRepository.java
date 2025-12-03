package com.example.aws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aws.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
