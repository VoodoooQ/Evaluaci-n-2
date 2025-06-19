import com.example.edutech.edutech.model.Usuario;
import com.example.edutech.edutech.service.UsuarioServiceImpl;

import org.junit.jupiter.api.*;

public class UsuarioSerciveTest {

    @Test
    public void AgregarUsuarioTest() {
        Usuario esperado = new Usuario(1,"Felipe","Farias","21043586-7",23,"estudiante",false);
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();

        Usuario resultado=
                usuarioService.saveUsuario(esperado);
        //Assertions.assertEquals(esperado, resultado);
        Assertions.assertEquals(esperado.getNombre(), resultado.getNombre());
    }

    @Test
    public void EliminarUsuarioTest() {

    }

    @Test
    public void BuscarUsuarioTest() {

    }


}