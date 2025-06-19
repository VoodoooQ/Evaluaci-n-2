package com.example.edutech.edutech.service;
import com.example.edutech.edutech.model.Inscripcion;

import java.util.List;
public interface InscripcionService {
    List<Inscripcion> getInscripciones();
    Inscripcion getInscripcion(String estudiante);

    List<Inscripcion> getInscripcion();

    Inscripcion saveInscripcion(Inscripcion inscripcion);
    Inscripcion updateInscripcion(Inscripcion inscripcion);

    Inscripcion updateInscripcion(String estudiante, Inscripcion inscripcion);

    String deleteInscripcion(String rut);

}
