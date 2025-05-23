package com.example.edutech.edutech.controlerCurso;

import com.example.edutech.edutech.model.Curso;
import com.example.edutech.edutech.service.CursoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping("/test")
    public String test() {
        return "Soy una maquina";
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.getCursos();
    }

    @PostMapping
    public Curso agregarCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable int id) {
        return cursoService.getCurso(id);
    }

    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        return cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("{id}")
    public String eliminarCurso(@PathVariable int id) {
        return cursoService.deleteCurso(id);
    }
}