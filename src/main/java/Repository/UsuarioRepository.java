package Repository;

import model.Usuario;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.ArrayList;

@Repository
public class UsuarioRepository {

private static List<Usuario> listausuarios = new ArrayList<>();

public List<Usuario> getUsuarios() {
        return new ArrayList<>(listausuarios);
    }

    //buscar usuario por rut

    public static Usuario buscarPorRut(int rut){
        for (Usuario usuario : listausuarios){
            if (usuario.getRut()== rut){
                return usuario;
            }
        }
        return null;


    }

    public Usuario guardar (Usuario us){
        listausuarios.add(us);
        return us;
    }

    public static Usuario actualizar (Usuario us){

        int rut = 0;
        int rutPosicion = 0;

         for (int i = 0; i < listausuarios.size(); i++) {

            if (listausuarios.get(i).getRut() == us.getRut()) {
                rut = us.getRut();
                rutPosicion = i;
            }
        }
        Usuario usuario1 = new Usuario();
        usuario1.setRut(rut);
        usuario1.setNombre(us.getNombre());
        usuario1.setApellido(us.getApellido());
        usuario1.setEdad(usuario1.getEdad());
        usuario1.setTipoUsuario(us.getTipoUsuario());
        

        listausuarios.set(rutPosicion, usuario1);
        return usuario1;
    }
    
    public static void eliminar (int rut){

        Usuario usuario= buscarPorRut(rut);
        if (usuario != null){
            listausuarios.remove(usuario);
        }

    }
        
}


