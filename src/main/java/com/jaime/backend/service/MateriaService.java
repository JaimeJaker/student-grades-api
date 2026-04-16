package com.jaime.backend.service;

import com.jaime.backend.dto.MateriaRequestDTO;
import com.jaime.backend.dto.MateriaResponseDTO;
import com.jaime.backend.exception.ResourceNotFoundException;
import com.jaime.backend.model.Materia;
import com.jaime.backend.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    private MateriaResponseDTO mapToDTO(Materia materia) {
        MateriaResponseDTO dto = new MateriaResponseDTO();
        dto.setId(materia.getId());
        dto.setNombre(materia.getNombre());
        dto.setCodigo(materia.getCodigo());
        dto.setCreditos(materia.getCreditos());
        return dto;
    }

    public MateriaResponseDTO guardarMateria(MateriaRequestDTO request) {
        Materia materia = new Materia(request.getNombre(), request.getCodigo(), request.getCreditos());
        return mapToDTO(materiaRepository.save(materia));
    }

    public List<MateriaResponseDTO> obtenerMaterias() {
        return materiaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public MateriaResponseDTO obtenerPorId(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con id " + id));
        return mapToDTO(materia);
    }

    public void eliminarMateria(Long id) {
        if (!materiaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Materia no encontrada con id " + id);
        }
        materiaRepository.deleteById(id);
    }

    public MateriaResponseDTO actualizarMateria(Long id, MateriaRequestDTO request) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada con id " + id));
        materia.setNombre(request.getNombre());
        materia.setCodigo(request.getCodigo());
        materia.setCreditos(request.getCreditos());
        return mapToDTO(materiaRepository.save(materia));
    }
}
