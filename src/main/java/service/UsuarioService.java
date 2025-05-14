package service;

import model.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import Repository.UsuarioRepository;
@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    
    public Usuario saveUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    
    public Usuario getUsuario(int rut) {
        return UsuarioRepository.buscarPorRut(rut);
    }

    
    public Usuario updateUsuario(int rut, Usuario usuario) {
        return UsuarioRepository.actualizar(usuario);

    }

   
    public String deleteUsuario(int rut) {
        UsuarioRepository.eliminar(rut);

        return "usuario eliminado";
    }


}
