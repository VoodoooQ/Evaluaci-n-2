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
        // Crear un usuario para eliminar
        Usuario usuario = new Usuario(1, "Carlos", "Lopez", "12345678-9", 25, "profesor", false);
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();
        
        // Guardar el usuario
        usuarioService.saveUsuario(usuario);
        
        // Eliminar el usuario por RUT
        String resultado = usuarioService.deleteUsuario("12345678-9");
        
        // Verificar que se eliminó correctamente
        Assertions.assertEquals("Usuario eliminado", resultado);
        
        // Verificar que ya no existe
        Usuario usuarioEliminado = usuarioService.getUsuario("12345678-9");
        Assertions.assertNull(usuarioEliminado);
    }

    @Test
    public void BuscarUsuarioTest() {
        // Crear y guardar un usuario
        Usuario esperado = new Usuario(2, "Ana", "Martinez", "98765432-1", 28, "administrador", false);
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();
        
        usuarioService.saveUsuario(esperado);
        
        // Buscar el usuario por RUT
        Usuario resultado = usuarioService.getUsuario("98765432-1");
        
        // Verificar que se encontró correctamente
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(esperado.getNombre(), resultado.getNombre());
        Assertions.assertEquals(esperado.getApellido(), resultado.getApellido());
        Assertions.assertEquals(esperado.getRut(), resultado.getRut());
        Assertions.assertEquals(esperado.getEdad(), resultado.getEdad());
        Assertions.assertEquals(esperado.getTipoUsuario(), resultado.getTipoUsuario());
        Assertions.assertEquals(esperado.isBloqueado(), resultado.isBloqueado());
    }

}