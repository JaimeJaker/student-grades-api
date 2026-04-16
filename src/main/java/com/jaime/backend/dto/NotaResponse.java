package com.jaime.backend.dto;

import java.time.LocalDate;

public class NotaResponse {

    private Double valor;
    private LocalDate fecha;
    private String alumnoNombre;
    private String materiaNombre;

    public NotaResponse(Double valor, LocalDate fecha, String alumnoNombre, String materiaNombre) {
        this.valor = valor;
        this.fecha = fecha;
        this.alumnoNombre = alumnoNombre;
        this.materiaNombre = materiaNombre;
    }

    // getters
    public Double getValor() { return valor; }
    public LocalDate getFecha() { return fecha; }
    public String getAlumnoNombre() { return alumnoNombre; }
    public String getMateriaNombre() { return materiaNombre; }
}