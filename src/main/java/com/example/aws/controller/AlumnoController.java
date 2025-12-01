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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import com.example.aws.dto.AlumnoDTO;
import com.example.aws.dto.AlumnoUpdateDTO;
import com.example.aws.model.Alumno;
import com.example.aws.service.AlumnoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public ResponseEntity<?> createAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        Alumno alumnoCreado = this.alumnoService.createAlumno(alumnoDTO);
        URI Location = URI.create("/alumnos/" + alumnoCreado.getId());
        return ResponseEntity.created(Location).body(alumnoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoUpdateDTO alumnoUpdateDTO) {
        Alumno updatedAlumno = this.alumnoService.updateAlumno(id, alumnoUpdateDTO);
        return ResponseEntity.ok(updatedAlumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable Long id) {
        Alumno deletedAlumno = this.alumnoService.findAlumno(id);
        this.alumnoService.deleteAlumno(id);
        return ResponseEntity.ok(deletedAlumno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.alumnoService.findAlumno(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.alumnoService.findAll());
    }

    // -------- Additional Endpoints without implementation -------- //

    @PostMapping("/{id}/fotoPerfil")
    public ResponseEntity<?> uploadFotoPerfil(@PathVariable Long id, @RequestParam ("file") MultipartFile file) {
        String fotoPerfilUrl = this.alumnoService.uploadFotoPerfil(id, file);
        return ResponseEntity.ok(fotoPerfilUrl);
    }

    @PostMapping("/{id}/email")
    public ResponseEntity<?> sendAlumnoEmail(@PathVariable Long id) {
        this.alumnoService.sendAlumnoInfoEmail(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/session/login")
    public ResponseEntity<?> loginAlumno(@PathVariable Long id, @RequestBody String loginData) {
        // Implement login logic here
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/session/verify")
    public ResponseEntity<?> verifyAlumnoSession(@PathVariable Long id, @RequestBody String sessionData) {
        // Implement session verification logic here
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/session/logout")
    public ResponseEntity<?> logoutAlumno(@PathVariable Long id) {
        // Implement logout logic here
        return ResponseEntity.ok().build();
    }

}
