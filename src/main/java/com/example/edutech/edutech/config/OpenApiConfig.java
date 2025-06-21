package com.example.edutech.edutech.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Usuarios - EduTech")
                        .version("1.0.0")
                        .description("Esta API permite gestionar usuarios dentro de la plataforma EduTech. Se pueden crear, leer, actualizar, eliminar usuarios, así como modificar roles y estados de bloqueo.")
                );
    }
}
