package com.example.edutech.edutech.model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

public class Cliente extends Usuario {


    public Cliente(String nombre, String apellido, String rut, int edad, String tipoUsuario, String rol,boolean bloqueado, String curso) {
        super(nombre, apellido, rut, edad, tipoUsuario, rol, bloqueado);


    }
    public String curso;
}
