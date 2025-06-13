package com.example.edutech.edutech.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "CURSO")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String nombrecurso;
    @Column(nullable = false)
    String descripcion;
    @Column(nullable = false)
    String categoria;
    @Column(nullable = false)
    String fechaCreacion;

}
