package com.jaime.backend.service;

import com.jaime.backend.dto.AlumnoRequestDTO;
import com.jaime.backend.dto.AlumnoResponseDTO;
import com.jaime.backend.exception.ResourceNotFoundException;
import com.jaime.backend.model.Alumno;
import com.jaime.backend.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    private AlumnoResponseDTO mapToDTO(Alumno alumno) {
        AlumnoResponseDTO dto = new AlumnoResponseDTO();
        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setEmail(alumno.getEmail());
        dto.setFechaNacimiento(alumno.getFechaNacimiento());
        return dto;
    }

    public AlumnoResponseDTO guardarAlumno(AlumnoRequestDTO request) {
        Alumno alumno = new Alumno(request.getNombre(), request.getApellido(), request.getEmail(), request.getFechaNacimiento());
        return mapToDTO(alumnoRepository.save(alumno));
    }

    public List<AlumnoResponseDTO> obtenerAlumnos() {
        return alumnoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AlumnoResponseDTO obtenerPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id " + id));
        return mapToDTO(alumno);
    }

    public void eliminarAlumno(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Alumno no encontrado con id " + id);
        }
        alumnoRepository.deleteById(id);
    }

    public AlumnoResponseDTO actualizarAlumno(Long id, AlumnoRequestDTO request) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id " + id));
        alumno.setNombre(request.getNombre());
        alumno.setApellido(request.getApellido());
        alumno.setEmail(request.getEmail());
        alumno.setFechaNacimiento(request.getFechaNacimiento());
        return mapToDTO(alumnoRepository.save(alumno));
    }
}