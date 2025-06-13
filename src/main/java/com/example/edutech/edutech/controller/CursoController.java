package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Curso;
import com.example.edutech.edutech.service.CursoService;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name= "Cursos", description = "operaciones relacionadas con los cursos del sistema")

@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping("/test")
    public String test() {
        return "Soy una maquina";
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los cursos registrados en el sistema")

    public List<Curso> listarCursos() {
        return cursoService.getCursos();
    }

    @PostMapping
    @Operation(summary = "Registra un curso en el sistema")
    public Curso agregarCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca un curso registrado en el sistema")
    public Curso buscarCurso(@PathVariable int id) {
        return cursoService.getCurso(id);
    }

    @PutMapping("{id}")
    @Operation(summary = "Actualiza los datos de un curso registado en el sistema")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        return cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Elimina los datos de un curso registado en el sistema")
    public String eliminarCurso(@PathVariable int id) {
        return cursoService.deleteCurso(id);
    }
}