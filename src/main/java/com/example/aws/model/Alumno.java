package com.example.aws.model;

public class Alumno {
    private Long id;
    private String nombres;
    private String apellidos;
    private String matricula;
    private double promedio;

    public Alumno(Long id, String nombres, String apellidos, String matricula, double promedio) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.promedio = promedio;
    }
    
    public Alumno(String nombres, String apellidos, String matricula, double promedio) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.promedio = promedio;
    }

    public Alumno(String nombres, String apellidos, String matricula) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPromedio() {
        return this.promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

}
