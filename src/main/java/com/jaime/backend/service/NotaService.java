package com.jaime.backend.service;

import com.jaime.backend.dto.NotaRequest;
import com.jaime.backend.dto.NotaResponse;
import com.jaime.backend.exception.BadRequestException;
import com.jaime.backend.exception.ResourceNotFoundException;
import com.jaime.backend.model.*;
import com.jaime.backend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlumnoRepository alumnoRepository;
    private final MateriaRepository materiaRepository;

    public NotaService(NotaRepository notaRepository,
                       AlumnoRepository alumnoRepository,
                       MateriaRepository materiaRepository) {
        this.notaRepository = notaRepository;
        this.alumnoRepository = alumnoRepository;
        this.materiaRepository = materiaRepository;
    }

    public NotaResponse registrarNota(NotaRequest request) {

        if (request.getValor() < 0 || request.getValor() > 5) {
            throw new BadRequestException("Nota inválida, debe estar entre 0 y 5");
        }

        Alumno alumno = alumnoRepository.findById(request.getAlumnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id " + request.getAlumnoId()));

        Materia materia = materiaRepository.findById(request.getMateriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con id " + request.getMateriaId()));

        Nota nota = new Nota();
        nota.setValor(request.getValor());
        nota.setFechaRegistro(LocalDate.now());
        nota.setAlumno(alumno);
        nota.setMateria(materia);

        Nota savedNota = notaRepository.save(nota);
        return new NotaResponse(savedNota.getValor(), savedNota.getFechaRegistro(), alumno.getNombre(), materia.getNombre());
    }

    public List<NotaResponse> obtenerNotas() {
        return notaRepository.findAll().stream().map(nota -> new NotaResponse(
                nota.getValor(),
                nota.getFechaRegistro(),
                nota.getAlumno().getNombre(),
                nota.getMateria().getNombre()
        )).collect(Collectors.toList());
    }

    public List<NotaResponse> obtenerNotasPorAlumno(Long alumnoId) {
        if (!alumnoRepository.existsById(alumnoId)) {
            throw new ResourceNotFoundException("Alumno no encontrado con id " + alumnoId);
        }
        List<Nota> notas = notaRepository.findByAlumnoIdOrderByFechaRegistroDesc(alumnoId);

        return notas.stream().map(nota -> new NotaResponse(
                nota.getValor(),
                nota.getFechaRegistro(),
                nota.getAlumno().getNombre(),
                nota.getMateria().getNombre()
        )).collect(Collectors.toList());
    }

    public NotaResponse obtenerPorId(Long id) {
        Nota nota = notaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nota no encontrada con id " + id));
        return new NotaResponse(nota.getValor(), nota.getFechaRegistro(), nota.getAlumno().getNombre(), nota.getMateria().getNombre());
    }

    public void eliminarNota(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nota no encontrada con id " + id);
        }
        notaRepository.deleteById(id);
    }
}