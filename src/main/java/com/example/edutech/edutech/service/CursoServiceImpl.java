package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Curso;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    private List<Curso> cursos = new ArrayList<>();

    @Override
    public List<Curso> getCursos() {
        return cursos;
    }

    @Override
    public Curso saveCurso(Curso curso) {
        cursos.add(curso);
        return curso;
    }

    @Override
    public Curso getCurso(int id) {
        return cursos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Curso updateCurso(int id, Curso curso) {
        Curso existente = getCurso(id);
        if (existente != null) {
            existente.setNombre(curso.getNombre());
            existente.setDescripcion(curso.getDescripcion());
            existente.setCategoria(curso.getCategoria());
            existente.setFechaCreacion(curso.getFechaCreacion());
        }
        return existente;
    }

    @Override
    public String deleteCurso(int id) {
        Curso curso = getCurso(id);
        if (curso != null) {
            cursos.remove(curso);
            return "Curso eliminado";
        }
        return "Curso no encontrado";
    }
}
