package com.example.edutech.edutech.config;
import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            UsuarioRepository.guardado(new Usuario (1,"Felipe","Farias","21043586-7",23,"estudiante",false));

        };
    }

}
