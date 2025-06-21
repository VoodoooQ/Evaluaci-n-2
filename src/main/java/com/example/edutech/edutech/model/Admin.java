package com.example.edutech.edutech.model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Admin extends Usuario {

     public Admin(String nombre, String apellido, String rut, int edad, String tipoUsuario, String rol,boolean bloqueado) {
          super(nombre,apellido, rut, edad, tipoUsuario,rol, bloqueado);


     }

     public void gestionarUsuarios() {
          System.out.println("Gestionando usuarios del sistema...");
     }
}