package com.example.edutech.edutech.service;
import com.example.edutech.edutech.model.Inscripcion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public abstract class InscripcionServiceImpl implements InscripcionService {
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
            existente.setEstudiante(existente.getEstudiante());
            existente.setCurso(existente.getCurso());
            existente.setFechaInscripcion(existente.getFechaInscripcion());
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


}
