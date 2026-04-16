package com.jaime.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
@Getter
@Setter
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    // RELACIONES 🔥
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    public Nota() {}

    public Nota(Double valor, LocalDate fechaRegistro, Alumno alumno, Materia materia) {
        this.valor = valor;
        this.fechaRegistro = fechaRegistro;
        this.alumno = alumno;
        this.materia = materia;
    }
}
