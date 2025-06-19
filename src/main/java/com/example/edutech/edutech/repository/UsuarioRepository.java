package com.example.edutech.edutech.repository;
import com.example.edutech.edutech.model.Usuario;

public interface UsuarioRepository extends JpaRepositorytory<Usuario, Long> {


    static void guardado(Usuario usuario) {
    }
}
