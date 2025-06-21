package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private List<Usuario> clientes = new ArrayList<>();

    @Override
    public List<Usuario> getClientes() {
        return clientes;
    }

    @Override
    public Usuario saveCliente(Usuario cliente) {
        clientes.add(cliente);
        return cliente;
    }

    @Override
    public Usuario getCliente(String rut) {
        return clientes.stream().filter(c -> c.getRut().equals(rut)).findFirst().orElse(null);
    }

    @Override
    public Usuario updateCliente(String rut, Usuario cliente) {
        Usuario existente = getCliente(rut);
        if (existente != null) {
            existente.setNombre(cliente.getNombre());
            existente.setApellido(cliente.getApellido());
            existente.setEdad(cliente.getEdad());
            existente.setTipoUsuario(cliente.getTipoUsuario());
            existente.setRol(cliente.getRol());
            existente.setBloqueado(cliente.isBloqueado());
        }
        return existente;
    }

    @Override
    public String deleteCliente(String rut) {
        Usuario cliente = getCliente(rut);
        if (cliente != null) {
            clientes.remove(cliente);
            return "Cliente eliminado";
        }
        return "Cliente no encontrado";
    }
}
