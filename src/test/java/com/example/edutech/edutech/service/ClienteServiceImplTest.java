package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceImplTest {

    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioServiceImpl();
    }

    @Test
    void testSaveCliente() {
        Usuario cliente = new Usuario(id:1, rut:"11111111-1", nombre:"Laura", apellido:"Diaz", edad:28, rol:"Cliente", tipoUsuario:"User", bloqueado:false);
        usuarioService.saveUsuario(cliente);

        assertEquals(1, usuarioService.getUsuarios().size());
        assertEquals("Laura", usuarioService.getUsuarios().get(0).getNombre());
    }

    @Test
    void testBloquearDesbloquearCliente() {
        Usuario cliente = new Usuario(id:1, rut:"11111111-1", "Laura", "Diaz", edad:28, rol:"Cliente", "User", false);
        usuarioService.saveUsuario(cliente);

        usuarioService.bloquearUsuario("11111111-1");
        assertTrue(usuarioService.getUsuario("11111111-1").isBloqueado());

        usuarioService.desbloquearUsuario("11111111-1");
        assertFalse(usuarioService.getUsuario("11111111-1").isBloqueado());
    }

    @Test
    void testCambiarRolCliente() {
        Usuario cliente = new Usuario(rut:"11111111-1", "Laura", "Diaz", edad:28, rol:"Cliente", "User", false);
        usuarioService.saveUsuario(cliente);

        Usuario updated = usuarioService.cambiarRol("11111111-1", "Estudiante");
        assertEquals("Estudiante", updated.getRol());
    }
}
