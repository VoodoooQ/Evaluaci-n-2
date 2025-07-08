package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Curso;
import com.example.edutech.edutech.service.CursoService;
import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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
    @Operation(summary = "Obtiene todos los cursos registrados en el sistema",
               description = "Devuelve una lista de todos los cursos con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente")
    })
    public CollectionModel<EntityModel<Curso>> listarCursos() {
        List<EntityModel<Curso>> cursos = cursoService.getCursos().stream()
            .map(curso -> EntityModel.of(curso)
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                    .buscarCurso(curso.getId())).withSelfRel())
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                    .actualizarCurso(curso.getId(), curso)).withRel("update"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                    .eliminarCurso(curso.getId())).withRel("delete"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .listarUsuarios()).withRel("usuarios"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                    .listarInscripciones()).withRel("inscripciones")))
            .collect(Collectors.toList());

        return CollectionModel.of(cursos)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .agregarCurso(null)).withRel("create"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra un curso en el sistema",
               description = "Crea un nuevo curso y devuelve el curso creado con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de curso inválidos")
    })
    public EntityModel<Curso> agregarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.saveCurso(curso);
        
        return EntityModel.of(nuevoCurso)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .buscarCurso(nuevoCurso.getId())).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("all-cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .actualizarCurso(nuevoCurso.getId(), nuevoCurso)).withRel("update"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .eliminarCurso(nuevoCurso.getId())).withRel("delete"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca un curso registrado en el sistema",
               description = "Obtiene un curso específico por ID con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public EntityModel<Curso> buscarCurso(@PathVariable int id) {
        Curso curso = cursoService.getCurso(id);
        
        if (curso == null) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
        
        return EntityModel.of(curso)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .buscarCurso(id)).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("all-cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .actualizarCurso(id, curso)).withRel("update"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .eliminarCurso(id)).withRel("delete"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }

    @PutMapping("{id}")
    @Operation(summary = "Actualiza los datos de un curso registado en el sistema",
               description = "Modifica los datos de un curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public EntityModel<Curso> actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        Curso cursoActualizado = cursoService.updateCurso(id, curso);
        
        // VALIDACIÓN
        if (cursoActualizado == null) {
            throw new RuntimeException("No se pudo actualizar. Curso no encontrado con ID: " + id);
        }
        
        return EntityModel.of(cursoActualizado)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .buscarCurso(id)).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("all-cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .eliminarCurso(id)).withRel("delete"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .agregarCurso(null)).withRel("create"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Elimina los datos de un curso registado en el sistema",
               description = "Elimina un curso del sistema por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public EntityModel<String> eliminarCurso(@PathVariable int id) {
        String resultado = cursoService.deleteCurso(id);
        
        return EntityModel.of(resultado)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("all-cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .agregarCurso(null)).withRel("create"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }
}