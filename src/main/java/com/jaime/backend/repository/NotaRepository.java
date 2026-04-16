package com.jaime.backend.repository;

import com.jaime.backend.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlumnoId(Long alumnoId);
    List<Nota> findByAlumnoIdOrderByFechaRegistroDesc(Long alumnoId);
}