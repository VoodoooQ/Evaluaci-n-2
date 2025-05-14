package model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Admin extends Usuario {

     public Admin(String nombre, String apellido, int rut, int edad, String tipoUsuario) {
          super(nombre,apellido, rut, edad, tipoUsuario);


     }

     public void gestionarUsuarios() {
          System.out.println("Gestionando usuarios del sistema...");
     }
}