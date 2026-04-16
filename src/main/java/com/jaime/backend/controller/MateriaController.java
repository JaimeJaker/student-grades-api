package com.jaime.backend.controller;

import com.jaime.backend.dto.MateriaRequestDTO;
import com.jaime.backend.dto.MateriaResponseDTO;
import com.jaime.backend.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    public MateriaResponseDTO crearMateria(@Valid @RequestBody MateriaRequestDTO request) {
        return materiaService.guardarMateria(request);
    }

    @GetMapping
    public List<MateriaResponseDTO> listarMaterias() {
        return materiaService.obtenerMaterias();
    }

    @GetMapping("/{id}")
    public MateriaResponseDTO obtenerMateria(@PathVariable Long id) {
        return materiaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarMateria(@PathVariable Long id) {
        materiaService.eliminarMateria(id);
    }

    @PutMapping("/{id}")
    public MateriaResponseDTO actualizarMateria(@PathVariable Long id, @Valid @RequestBody MateriaRequestDTO request) {
        return materiaService.actualizarMateria(id, request);
    }
}
