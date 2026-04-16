package com.jaime.backend.controller;

import com.jaime.backend.dto.NotaRequest;
import com.jaime.backend.dto.NotaResponse;
import com.jaime.backend.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaResponse crearNota(@Valid @RequestBody NotaRequest request) {
        return notaService.registrarNota(request);
    }

    @GetMapping
    public List<NotaResponse> listarNotas() {
        return notaService.obtenerNotas();
    }

    @GetMapping("/{id}")
    public NotaResponse obtenerNota(@PathVariable Long id) {
        return notaService.obtenerPorId(id);
    }

    @GetMapping("/alumno/{id}")
    public List<NotaResponse> listarNotasPorAlumno(@PathVariable Long id) {
        return notaService.obtenerNotasPorAlumno(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarNota(@PathVariable Long id) {
        notaService.eliminarNota(id);
    }
}