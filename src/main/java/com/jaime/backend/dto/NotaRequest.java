package com.jaime.backend.dto;

import jakarta.validation.constraints.NotNull;

public class NotaRequest {

    @NotNull(message = "El valor es obligatorio")
    private Double valor;
    @NotNull(message = "El ID del alumno es obligatorio")
    private Long alumnoId;
    @NotNull(message = "El ID de la materia es obligatorio")
    private Long materiaId;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }
}