package com.example.edutech.edutech.config;
import com.example.edutech.edutech.repository.InscripcionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.edutech.edutech.model.Inscripcion;
@Configuration


public class DataLoader {
    @Bean
    CommandLineRunner initDatabase(InscripcionRepository inscripcionRepository) {
        return args -> {
            inscripcionRepository.save(new Inscripcion("Ana Gómez", "Bases de Datos", "2025-06-10"));
            inscripcionRepository.save(new Inscripcion("Carlos Díaz", "Redes I", "2025-06-01"));
        };
    }
}
