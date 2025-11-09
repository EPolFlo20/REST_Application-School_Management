package com.example.aws.model;

public class Profesor {
    private Long id;
    private String nombres;
    private String apellidos;
    private int numeroEmpleado;
    private double horasClase;

    public Profesor(Long id, String nombres, String apellidos, int numeroEmpleado, double horasClase) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroEmpleado = numeroEmpleado;
        this.horasClase = horasClase;
    }
    
    public Profesor(String nombres, String apellidos, int numeroEmpleado, double horasClase) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroEmpleado = numeroEmpleado;
        this.horasClase = horasClase;
    }

    public Profesor(String nombres, String apellidos, int numeroEmpleado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroEmpleado = numeroEmpleado;
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

    public int getNumeroEmpleado() {
        return this.numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public double getHorasClase() {
        return this.horasClase;
    }

    public void setHorasClase(double horasClase) {
        this.horasClase = horasClase;
    }

}
