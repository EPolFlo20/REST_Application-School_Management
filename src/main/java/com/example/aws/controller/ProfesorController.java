package com.example.aws.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.aws.dto.ProfesorDTO;
import com.example.aws.dto.ProfesorUpdateDTO;
import com.example.aws.model.Profesor;
import com.example.aws.service.ProfesorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<?> createProfesor(@Valid @RequestBody ProfesorDTO profesorDTO) {
        Profesor profesorCreado = this.profesorService.createProfesor(profesorDTO);
        URI Location = URI.create("/profesores/" + profesorCreado.getId());
        return ResponseEntity.created(Location).body(profesorCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfesor(@PathVariable Long id,
            @Valid @RequestBody ProfesorUpdateDTO profesorUpdateDTO) {
        Profesor updatedProfesor = this.profesorService.updateProfesor(id, profesorUpdateDTO);
        return ResponseEntity.ok(updatedProfesor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable Long id) {
        Profesor deletedProfesor = this.profesorService.findProfesor(id);
        this.profesorService.deleteProfesor(id);
        return ResponseEntity.ok(deletedProfesor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.profesorService.findProfesor(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.profesorService.findAll());
    }
}
