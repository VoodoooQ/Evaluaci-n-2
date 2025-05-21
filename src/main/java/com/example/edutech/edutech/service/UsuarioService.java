package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario saveUsuario(Usuario usuario);
    Usuario getUsuario(String rut);
    Usuario updateUsuario(String rut, Usuario usuario);
    String deleteUsuario(String rut);
}