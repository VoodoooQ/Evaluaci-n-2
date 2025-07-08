package com.example.edutech.edutech.service;
import com.example.edutech.edutech.model.Inscripcion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InscripcionServiceImpl implements InscripcionService {
   private List<Inscripcion> inscripciones = new ArrayList<>();

    @Override
    public List<Inscripcion> getInscripcion() {return inscripciones;}

    @Override
    public Inscripcion saveInscripcion(Inscripcion inscripcion){
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    @Override
    public Inscripcion getInscripcion(String estudiante){
        return inscripciones.stream().filter(u -> u.getEstudiante().equals(estudiante)).findFirst().orElse(null);


    }

    @Override
    public Inscripcion updateInscripcion(String estudiante, Inscripcion inscripcion){
        Inscripcion existente = getInscripcion(estudiante);
        if(existente != null) {
            existente.setEstudiante(inscripcion.getEstudiante());
            existente.setCurso(inscripcion.getCurso());
            existente.setFechaInscripcion(inscripcion.getFechaInscripcion());
        }
        return  existente;
    }
    @Override
    public String deleteInscripcion(String estudiante){
        Inscripcion inscripcion = getInscripcion(estudiante);
        if(inscripcion != null) {
            inscripciones.remove(inscripcion);
            return "Inscripcion eliminada";
        }
        return "Inscripcion no encontrada";
    }

    @Override
    public List<Inscripcion> getInscripciones() {
        // Devuelve todas las inscripciones
        return inscripciones;
    }

    @Override
    public Inscripcion updateInscripcion(Inscripcion inscripcion) {
        // Busca la inscripción por estudiante y actualiza si existe
        Inscripcion existente = getInscripcion(inscripcion.getEstudiante());
        if (existente != null) {
            existente.setCurso(inscripcion.getCurso());
            existente.setFechaInscripcion(inscripcion.getFechaInscripcion());
        }
        return existente;
    }


}
