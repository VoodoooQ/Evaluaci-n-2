package service;

import model.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Usuario getUsuario(String rut) {
        return usuarios.stream()
                .filter(u -> u.getRut().equals(rut))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuario updateUsuario(String rut, Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getRut().equals(rut)) {
                usuarios.set(i, usuarioActualizado);
                return usuarioActualizado;
            }
        }
        return null;
    }

    @Override
    public String deleteUsuario(String rut) {
        boolean eliminado = usuarios.removeIf(u -> u.getRut().equals(rut));
        return eliminado ? "Usuario eliminado" : "Usuario no encontrado";
    }
}