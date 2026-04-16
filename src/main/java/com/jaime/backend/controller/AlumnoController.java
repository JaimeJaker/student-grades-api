package com.jaime.backend.controller;

import com.jaime.backend.dto.AlumnoRequestDTO;
import com.jaime.backend.dto.AlumnoResponseDTO;
import com.jaime.backend.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public AlumnoResponseDTO crearAlumno(@Valid @RequestBody AlumnoRequestDTO request) {
        return alumnoService.guardarAlumno(request);
    }

    @GetMapping
    public List<AlumnoResponseDTO> listarAlumnos() {
        return alumnoService.obtenerAlumnos();
    }

    @GetMapping("/{id}")
    public AlumnoResponseDTO obtenerAlumno(@PathVariable Long id) {
        return alumnoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarAlumno(@PathVariable Long id) {
        alumnoService.eliminarAlumno(id);
    }

    @PutMapping("/{id}")
    public AlumnoResponseDTO actualizarAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoRequestDTO request) {
        return alumnoService.actualizarAlumno(id, request);
    }
}