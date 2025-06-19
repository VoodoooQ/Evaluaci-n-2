package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Inscripcion;
import com.example.edutech.edutech.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> getInscripciones() {
        return inscripcionService.getInscripciones();
    }

    @PostMapping
    public Inscripcion saveInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.saveInscripcion(inscripcion);
    }

    @GetMapping ({"estudiante"})
    public Inscripcion getInscripcion(@PathVariable String estudiante) {
        return inscripcionService.getInscripcion(estudiante);
    }

    @PutMapping({"estudiante"})
    public Inscripcion updateInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.updateInscripcion(inscripcion);
    }
    @DeleteMapping({"estudiante"})
    public String deleteInscripcion(@PathVariable String estudiante) {
       return inscripcionService.deleteInscripcion(estudiante);

    }
}
