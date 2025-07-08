package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Inscripcion;
import com.example.edutech.edutech.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/inscripcion")
@Tag(name= "Inscripciones", description = "operaciones relacionadas con las inscripciones del sistema")
public class InscripcionController {
    
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    @Operation(summary = "Obtiene todas las inscripciones registradas en el sistema",
               description = "Devuelve una lista de todas las inscripciones con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de inscripciones obtenida exitosamente")
    })
    public CollectionModel<EntityModel<Inscripcion>> listarInscripciones() { // ✅ Nombre consistente
        List<EntityModel<Inscripcion>> inscripciones = inscripcionService.getInscripciones().stream()
            .map(inscripcion -> EntityModel.of(inscripcion)
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                    .buscarInscripcion(inscripcion.getEstudiante())).withSelfRel()) // ✅ Corregido
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                    .actualizarInscripcion(inscripcion.getEstudiante(), inscripcion)).withRel("update")) // ✅ Corregido
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                    .eliminarInscripcion(inscripcion.getEstudiante())).withRel("delete")) // ✅ Corregido
                // ✅ Referencias corregidas usando métodos específicos
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .listarUsuarios()).withRel("usuarios"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                    .listarCursos()).withRel("cursos")))
            .collect(Collectors.toList());

        return CollectionModel.of(inscripciones)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withSelfRel()) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .agregarInscripcion(null)).withRel("create")); // ✅ Corregido
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // ✅ Código de estado correcto
    @Operation(summary = "Registra una inscripción en el sistema",
               description = "Crea una nueva inscripción y devuelve la inscripción creada con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inscripción creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de inscripción inválidos")
    })
    public EntityModel<Inscripcion> agregarInscripcion(@RequestBody Inscripcion inscripcion) { // ✅ Nombre consistente
        Inscripcion nuevaInscripcion = inscripcionService.saveInscripcion(inscripcion);
        
        return EntityModel.of(nuevaInscripcion)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .buscarInscripcion(nuevaInscripcion.getEstudiante())).withSelfRel()) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("all-inscripciones")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .actualizarInscripcion(nuevaInscripcion.getEstudiante(), nuevaInscripcion)).withRel("update")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .eliminarInscripcion(nuevaInscripcion.getEstudiante())).withRel("delete")) // ✅ Corregido
            // ✅ Referencias corregidas
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"));
    }

    @GetMapping("{estudiante}")
    @Operation(summary = "Busca una inscripción registrada en el sistema",
               description = "Obtiene una inscripción específica por estudiante con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscripción encontrada"),
        @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    public EntityModel<Inscripcion> buscarInscripcion(@PathVariable String estudiante) { // ✅ Nombre consistente
        Inscripcion inscripcion = inscripcionService.getInscripcion(estudiante);
        
        // ✅ Manejo de errores agregado
        if (inscripcion == null) {
            throw new RuntimeException("Inscripción no encontrada para el estudiante: " + estudiante);
        }
        
        return EntityModel.of(inscripcion)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .buscarInscripcion(estudiante)).withSelfRel()) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("all-inscripciones")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .actualizarInscripcion(estudiante, inscripcion)).withRel("update")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .eliminarInscripcion(estudiante)).withRel("delete")) // ✅ Corregido
            // ✅ Referencias corregidas
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"));
    }

    @PutMapping("{estudiante}")
    @Operation(summary = "Actualiza una inscripción registrada en el sistema",
               description = "Modifica los datos de una inscripción existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscripción actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    // ✅ PROBLEMA PRINCIPAL CORREGIDO: Agregado @PathVariable String estudiante
    public EntityModel<Inscripcion> actualizarInscripcion(@PathVariable String estudiante, @RequestBody Inscripcion inscripcion) {
        Inscripcion inscripcionActualizada = inscripcionService.updateInscripcion(estudiante, inscripcion);
        
        // ✅ Manejo de errores agregado
        if (inscripcionActualizada == null) {
            throw new RuntimeException("No se pudo actualizar. Inscripción no encontrada para el estudiante: " + estudiante);
        }
        
        return EntityModel.of(inscripcionActualizada)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .buscarInscripcion(inscripcionActualizada.getEstudiante())).withSelfRel()) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("all-inscripciones")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .eliminarInscripcion(inscripcionActualizada.getEstudiante())).withRel("delete")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .agregarInscripcion(null)).withRel("create")) // ✅ Corregido
            // ✅ Referencias corregidas
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"));
    }

    @DeleteMapping("{estudiante}")
    @Operation(summary = "Elimina una inscripción registrada en el sistema",
               description = "Elimina una inscripción del sistema por estudiante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscripción eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Inscripción no encontrada")
    })
    public EntityModel<String> eliminarInscripcion(@PathVariable String estudiante) { // ✅ Nombre consistente
        String resultado = inscripcionService.deleteInscripcion(estudiante);
        
        return EntityModel.of(resultado)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("all-inscripciones")) // ✅ Corregido
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .agregarInscripcion(null)).withRel("create")) // ✅ Corregido
            // ✅ Referencias corregidas
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"));
    }
}
