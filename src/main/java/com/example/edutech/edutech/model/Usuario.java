package com.example.edutech.edutech.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
        @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
        private int id;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false)
        private String apellido;

        @Column(nullable = false)
        private String rut;

        @Column(nullable = false)
        private int edad;

        @Column(name = "TIPO_USUARIO", nullable = false)
        private String tipoUsuario;


        @Column(nullable = false)
        private boolean bloqueado;


}
