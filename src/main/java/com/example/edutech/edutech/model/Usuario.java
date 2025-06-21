package com.example.edutech.edutech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String rut;
    private String nombre;
    private String apellido;
    private int edad;
    private String tipoUsuario;
    private String rol;
    private boolean bloqueado;

}

