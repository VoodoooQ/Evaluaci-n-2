package model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

        String nombre;
        String apellido;
        String rut;
        int edad;
        String tipoUsuario;

        public Usuario(String nombre,String apellido, String rut, int edad, String tipoUsuario) {
        }
}


