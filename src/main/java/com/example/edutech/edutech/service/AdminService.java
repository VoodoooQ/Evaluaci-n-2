package com.example.edutech.edutech.service;

import com.example.edutech.edutech.model.Usuario;
import java.util.List;

public interface AdminService {
List<Usuario> getAdmins();
Usuario getAdmin(String rut);
Usuario saveAdmin(Usuario admin);
Usuario updateAdmin(String rut, Usuario admin);
String deleteAdmin(String rut);
}