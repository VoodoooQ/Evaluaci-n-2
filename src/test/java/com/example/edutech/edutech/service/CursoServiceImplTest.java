package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Curso;
import com.example.edutech.edutech.service.CursoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CursoServiceImplTest {

    private CursoServiceImpl cursoService;

    @BeforeEach
    void setUp() {
        cursoService = new CursoServiceImpl();
    }

    @Test
    void testSaveCurso() {
        Curso curso = new Curso(1, "Matemáticas", "Curso de matemáticas básicas", "Ciencias", "2024-06-20");
        cursoService.saveCurso(curso);

        assertEquals(1, cursoService.getCursos().size());
        assertEquals("Matemáticas", cursoService.getCursos().get(0).getNombre());
    }

    @Test
    void testGetCurso() {
        Curso curso = new Curso(1, "Matemáticas", "Curso de matemáticas básicas", "Ciencias", "2024-06-20");
        cursoService.saveCurso(curso);

        Curso result = cursoService.getCurso(1);
        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombre());
    }

    @Test
    void testUpdateCurso() {
        Curso curso = new Curso(1, "Matemáticas", "Curso básico", "Ciencias", "2024-06-20");
        cursoService.saveCurso(curso);

        Curso updated = new Curso(1, "Física", "Curso avanzado de física", "Ciencias Exactas", "2024-06-21");
        cursoService.updateCurso(1, updated);

        Curso result = cursoService.getCurso(1);
        assertEquals("Física", result.getNombre());
        assertEquals("Curso avanzado de física", result.getDescripcion());
        assertEquals("Ciencias Exactas", result.getCategoria());
        assertEquals("2024-06-21", result.getFechaCreacion());
    }

    @Test
    void testDeleteCurso() {
        Curso curso = new Curso(1, "Matemáticas", "Curso básico", "Ciencias", "2024-06-20");
        cursoService.saveCurso(curso);

        String msg = cursoService.deleteCurso(1);
        assertEquals("Curso eliminado", msg);
        assertTrue(cursoService.getCursos().isEmpty());
    }
}
