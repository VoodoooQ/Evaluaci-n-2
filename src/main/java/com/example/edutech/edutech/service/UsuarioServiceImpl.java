package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public List<Usuario> getUsuarios() { return usuarios; }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Usuario getUsuario(String rut) {
        return usuarios.stream().filter(u -> u.getRut().equals(rut)).findFirst().orElse(null);
    }

    @Override
    public Usuario updateUsuario(String rut, Usuario usuario) {
        Usuario existente = getUsuario(rut);
        if (existente != null) {
            existente.setNombre(usuario.getNombre());
            existente.setApellido(usuario.getApellido());
            existente.setEdad(usuario.getEdad());
            existente.setTipoUsuario(usuario.getTipoUsuario());
            existente.setRol(usuario.getRol());
            existente.setBloqueado(usuario.isBloqueado());
        }
        return existente;
    }

    @Override
    public String deleteUsuario(String rut) {
        Usuario usuario = getUsuario(rut);
        if (usuario != null) {
            usuarios.remove(usuario);
            return "Usuario eliminado";
        }
        return "Usuario no encontrado";
    }


}