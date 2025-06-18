package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Inscripcion;
import com.example.edutech.edutech.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    // ✅ Constructor explícito
    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> getInscripcionById(Long id) {
        return inscripcionRepository.findById(id);
    }

    public Inscripcion createInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
