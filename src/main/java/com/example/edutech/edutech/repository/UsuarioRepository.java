package com.example.edutech.edutech.repository;
import com.example.edutech.edutech.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepositorytory<Usuario, Long> {
    static void save(Usuario usuario) {

    }
}
