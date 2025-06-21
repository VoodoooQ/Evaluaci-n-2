package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceImplTest {

    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioServiceImpl();
    }

    @Test
    void testSaveUsuario() {
        Usuario usuario = new Usuario(rut:"12345678-9", "Juan", "Perez", edad:30, tipoUsuario:"Administrador", rol:"Admin", false);
        usuarioService.saveUsuario(usuario);

        assertEquals(1, usuarioService.getUsuarios().size());
        assertEquals("Juan", usuarioService.getUsuarios().get(0).getNombre());
    }

    @Test
    void testGetUsuario() {
        Usuario usuario = new Usuario(rut:"12345678-9", "Juan", "Perez", edad:30, tipoUsuario"Administrador", rol:"Admin", false);
        usuarioService.saveUsuario(usuario);

        Usuario result = usuarioService.getUsuario("12345678-9");
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testUpdateUsuario() {
        Usuario usuario = new Usuario(rut:"12345678-9", "Juan", "Perez", edad:30, tipoUsuario:"Administrador", rol:"Admin", false);
        usuarioService.saveUsuario(usuario);

        Usuario updated = new Usuario(rut:"12345678-9", "Pedro", "Gomez", edad:35, tipoUsuario"Usuario", rol:"User", true);
        usuarioService.updateUsuario("12345678-9", updated);

        Usuario result = usuarioService.getUsuario("12345678-9");
        assertEquals("Pedro", result.getNombre());
        assertEquals(35, result.getEdad());
    }

    @Test
    void testDeleteUsuario() {
        Usuario usuario = new Usuario(rut:"12345678-9", "Juan", "Perez", edad:30, tipoUsuario:"Administrador", rol:"Admin", false);
        usuarioService.saveUsuario(usuario);

        String message = usuarioService.deleteUsuario("12345678-9");
        assertEquals("Usuario eliminado", message);
        assertTrue(usuarioService.getUsuarios().isEmpty());
    }

    @Test
    void testCambiarRol() {
        Usuario usuario = new Usuario("12345678-9", "Juan", "Perez", 30, "Administrador", "Admin", false);
        usuarioService.saveUsuario(usuario);

        Usuario updated = usuarioService.cambiarRol("12345678-9", "User");
        assertEquals("User", updated.getRol());
    }

    @Test
    void testBloquearDesbloquearUsuario() {
        Usuario usuario = new Usuario("12345678-9", "Juan", "Perez", 30, "Administrador", "Admin", false);
        usuarioService.saveUsuario(usuario);

        usuarioService.bloquearUsuario("12345678-9");
        assertTrue(usuarioService.getUsuario("12345678-9").isBloqueado());

        usuarioService.desbloquearUsuario("12345678-9");
        assertFalse(usuarioService.getUsuario("12345678-9").isBloqueado());
    }
}
