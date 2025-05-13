package service;

import model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario saveUsuario(Usuario usuario);
    Usuario getUsuario(String rut);
    Usuario updateUsuario(String rut, Usuario usuario);
    String deleteUsuario(String rut);
    Usuario cambiarRol(String rut, String nuevoRol);
    Usuario bloquearUsuario(String rut);
    Usuario desbloquearUsuario(String rut);
}