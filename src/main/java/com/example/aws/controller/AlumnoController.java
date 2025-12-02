package com.example.aws.controller;

import java.net.URI;
import java.util.Map;

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

import com.example.aws.components.AlumnoMessageBuilder;
import com.example.aws.dto.AlumnoDTO;
import com.example.aws.dto.AlumnoUpdateDTO;
import com.example.aws.model.Alumno;
import com.example.aws.service.AlumnoService;
import com.example.aws.service.impl.SnsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final SnsService snsService;

    public AlumnoController(AlumnoService alumnoService, SnsService snsService) {
        this.alumnoService = alumnoService;
        this.snsService = snsService;
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

    @PostMapping(path = "/{id}/fotoPerfil")
    public ResponseEntity<?> uploadFotoPerfil(@PathVariable Long id, @RequestParam("foto") MultipartFile file) {
        String fotoPerfilUrl = this.alumnoService.uploadFotoPerfil(id, file);
        System.out.println("Foto de perfil subida a URL: " + fotoPerfilUrl);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Foto de perfil subida exitosamente",
                "fotoPerfilUrl", fotoPerfilUrl));
    }

    @PostMapping("/{id}/email")
    public ResponseEntity<?> sendAlumnoEmail(@PathVariable Long id) {

        Alumno alumno = this.alumnoService.findAlumno(id);

        String message = AlumnoMessageBuilder.buildAlumnoMessage(
                alumno,
                "Envío de información del alumno por correo electrónico");

        this.snsService.publish(message);

        return ResponseEntity.ok(Map.of("mensaje", "Mensaje enviado vía SNS"));
    }

    // -------- Additional Endpoints without implementation -------- //

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
