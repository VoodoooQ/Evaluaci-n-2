package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {

    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioServiceImpl();
    }

    @Test
    void testSaveAdmin() {
        Usuario admin = new Usuario("22222222-2", "Carlos", "Soto", 40, "Administrador", "Admin", false);
        usuarioService.saveUsuario(admin);

        assertEquals(1, usuarioService.getUsuarios().size());
        assertEquals("Carlos", usuarioService.getUsuarios().get(0).getNombre());
    }

    @Test
    void testBloquearDesbloquearAdmin() {
        Usuario admin = new Usuario("22222222-2", "Carlos", "Soto", 40, "Administrador", "Admin", false);
        usuarioService.saveUsuario(admin);

        usuarioService.bloquearUsuario("22222222-2");
        assertTrue(usuarioService.getUsuario("22222222-2").isBloqueado());

        usuarioService.desbloquearUsuario("22222222-2");
        assertFalse(usuarioService.getUsuario("22222222-2").isBloqueado());
    }

    @Test
    void testCambiarRolAdmin() {
        Usuario admin = new Usuario("22222222-2", "Carlos", "Soto", 40, "Administrador", "Admin", false);
        usuarioService.saveUsuario(admin);

        Usuario updated = usuarioService.cambiarRol("22222222-2", "SuperAdmin");
        assertEquals("SuperAdmin", updated.getRol());
    }
}
