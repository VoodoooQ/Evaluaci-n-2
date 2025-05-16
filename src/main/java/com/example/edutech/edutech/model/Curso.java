package com.example.edutech.edutech.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    int id;
    String nombre;
    String descripcion;
    String categoria;
    String fechaCreacion;

}
