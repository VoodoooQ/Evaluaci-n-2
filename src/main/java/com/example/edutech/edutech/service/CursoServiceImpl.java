package com.example.edutech.edutech.service;
import com.example.edutech.edutech.model.Curso;
import java.util.ArrayList;
import java.util.List;

import com.example.edutech.edutech.model.Curso;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {
    private List<Curso> cursos = new ArrayList<>();

    @Override
    public List<Curso> getCursos() { return cursos; }

    @Override
    public Curso saveCurso(Curso curso) {
        return null;
    }

    @Override
    public Curso getCurso(int id) {
        return null;
    }

    @Override
    public Curso updateCurso(int id, Curso curso) {
        return null;
    }

    @Override
    public String deleteCurso(int id) {
        return "Curso eliminado";
    }


}
