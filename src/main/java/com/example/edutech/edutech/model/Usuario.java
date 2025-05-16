package com.example.edutech.edutech.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

        private String nombre;
        private String apellido;
        private String rut;
        private int edad;
        private String tipoUsuario;
        private String rol;
        private boolean bloqueado;


}
