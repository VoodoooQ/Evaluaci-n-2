package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name= "Usuarios", description = "operaciones relacionadas con los usuarios del sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/test")
    public String test() {
        return "Soy una maquina";
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los usuarios registrados en el sistema", 
               description = "Devuelve una lista de todos los usuarios con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    })
    public CollectionModel<EntityModel<Usuario>> listarUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.getUsuarios().stream()
            .map(usuario -> EntityModel.of(usuario)
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .buscarUsuario(usuario.getRut())).withSelfRel())
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .actualizarUsuario(usuario.getRut(), usuario)).withRel("update"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .eliminarUsuario(usuario.getRut())).withRel("delete"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                    .listarCursos()).withRel("cursos"))
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                    .listarInscripciones()).withRel("inscripciones")))
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .agregarUsuario(null)).withRel("create"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Agrega un usuario al sistema",
               description = "Crea un nuevo usuario y devuelve el usuario creado con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos")
    })
    public EntityModel<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.saveUsuario(usuario);
        
        return EntityModel.of(nuevoUsuario)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuario(nuevoUsuario.getRut())).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("all-usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .actualizarUsuario(nuevoUsuario.getRut(), nuevoUsuario)).withRel("update"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .eliminarUsuario(nuevoUsuario.getRut())).withRel("delete"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }

    @GetMapping("{rut}")
    @Operation(summary = "Busca un usuario registrado en el sistema",
               description = "Obtiene un usuario específico por RUT con enlaces HATEOAS")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public EntityModel<Usuario> buscarUsuario(@PathVariable String rut) {
        Usuario usuario = usuarioService.getUsuario(rut);
        
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con RUT: " + rut);
        }
        
        return EntityModel.of(usuario)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuario(rut)).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("all-usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .actualizarUsuario(rut, usuario)).withRel("update"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .eliminarUsuario(rut)).withRel("delete"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }

    @PutMapping("{rut}")
    @Operation(summary = "Actualiza un usuario registrado en el sistema",
               description = "Modifica los datos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public EntityModel<Usuario> actualizarUsuario(@PathVariable String rut, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.updateUsuario(rut, usuario);
        
        // Validación 
        if (usuarioActualizado == null) {
            throw new RuntimeException("No se pudo actualizar. Usuario no encontrado con RUT: " + rut);
        }
        
        return EntityModel.of(usuarioActualizado)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuario(rut)).withSelfRel())
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("all-usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .eliminarUsuario(rut)).withRel("delete"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .agregarUsuario(null)).withRel("create"))  // ← ESTA LÍNEA ES LA QUE FALTA
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }

    @DeleteMapping("{rut}")
    @Operation(summary = "Elimina un usuario registrado en el sistema",
               description = "Elimina un usuario del sistema por RUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public EntityModel<String> eliminarUsuario(@PathVariable String rut) {
        String resultado = usuarioService.deleteUsuario(rut);
        
        return EntityModel.of(resultado)
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .listarUsuarios()).withRel("all-usuarios"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .agregarUsuario(null)).withRel("create"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class)
                .listarCursos()).withRel("cursos"))
            .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InscripcionController.class)
                .listarInscripciones()).withRel("inscripciones"));
    }
}