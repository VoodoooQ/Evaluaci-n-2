package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> getCursos();
    Curso saveCurso(Curso curso);
    Curso getCurso(int id);
    Curso updateCurso(int id, Curso curso);
    String deleteCurso(int id);
}
