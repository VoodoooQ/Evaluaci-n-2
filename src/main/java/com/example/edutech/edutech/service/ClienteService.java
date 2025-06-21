package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import java.util.List;

public interface ClienteService {
List<Usuario> getClientes();
Usuario getCliente(String rut);
Usuario saveCliente(Usuario cliente);
Usuario updateCliente(String rut, Usuario cliente);
String deleteCliente(String rut);
}
