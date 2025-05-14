package model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

        private String nombre;
        private String apellido;
        private int rut;
        private int edad;
        private String tipoUsuario;


}


