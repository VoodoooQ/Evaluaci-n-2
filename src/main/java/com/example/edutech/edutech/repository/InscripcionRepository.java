package com.example.edutech.edutech.repository;

import com.example.edutech.edutech.model.Inscripcion;

import java.util.List;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepositorytory<Inscripcion, Long>{
    List<Inscripcion> findAll();

    Optional<Inscripcion> findById(Long id);

    Inscripcion save(Inscripcion inscripcion);

    void deleteById(Long id);
}
