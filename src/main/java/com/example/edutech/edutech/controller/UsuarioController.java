package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(summary = "Obtiene todos los usuarios registrados en el sistema")

    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    @Operation(summary = "Agrega un usuario al sistema")

    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("{rut}")
    @Operation(summary = "Busca un usuario registrado en el sistema")
    public Usuario buscarUsuario(@PathVariable String rut) {
        return usuarioService.getUsuario(rut);
    }

    @PutMapping("{rut}")
    @Operation(summary = "Actualiza un usuario registrado en el sistema")
    public Usuario actualizarUsuario(@PathVariable String rut, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(rut, usuario);
    }

    @DeleteMapping("{rut}")
    @Operation(summary = "Elimina un usuario registrado en el sistema")
    public String eliminarUsuario(@PathVariable String rut) {
        return usuarioService.deleteUsuario(rut);
    }

}