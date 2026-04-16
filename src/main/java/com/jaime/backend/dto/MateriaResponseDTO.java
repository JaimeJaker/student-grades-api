package com.jaime.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaResponseDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private Integer creditos;
}
