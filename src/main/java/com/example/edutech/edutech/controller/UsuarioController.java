package com.example.edutech.edutech.controller;

import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/test")
    public String test() {
        return "Soy una maquina";
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("{rut}")
    public Usuario buscarUsuario(@PathVariable String rut) {
        return usuarioService.getUsuario(rut);
    }

    @PutMapping("{rut}")
    public Usuario actualizarUsuario(@PathVariable String rut, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(rut, usuario);
    }

    @DeleteMapping("{rut}")
    public String eliminarUsuario(@PathVariable String rut) {
        return usuarioService.deleteUsuario(rut);
    }
}