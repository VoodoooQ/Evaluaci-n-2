package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private List<Usuario> admins = new ArrayList<>();

    @Override
    public List<Usuario> getAdmins() {
        return admins;
    }

    @Override
    public Usuario saveAdmin(Usuario admin) {
        admins.add(admin);
        return admin;
    }

    @Override
    public Usuario getAdmin(String rut) {
        return admins.stream().filter(a -> a.getRut().equals(rut)).findFirst().orElse(null);
    }

    @Override
    public Usuario updateAdmin(String rut, Usuario admin) {
        Usuario existente = getAdmin(rut);
        if (existente != null) {
            existente.setNombre(admin.getNombre());
            existente.setApellido(admin.getApellido());
            existente.setEdad(admin.getEdad());
            existente.setTipoUsuario(admin.getTipoUsuario());
            existente.setRol(admin.getRol());
            existente.setBloqueado(admin.isBloqueado());
        }
        return existente;
    }

    @Override
    public String deleteAdmin(String rut) {
        Usuario admin = getAdmin(rut);
        if (admin != null) {
            admins.remove(admin);
            return "Administrador eliminado";
        }
        return "Administrador no encontrado";
    }
}
