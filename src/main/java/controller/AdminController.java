package controller;

import model.Usuario;
import service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/usuarios")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    // 2. Agregar un usuario
    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    // 3. Buscar usuario por RUT
    @GetMapping("/{rut}")
    public Usuario buscarUsuario(@PathVariable String rut) {
        return usuarioService.getUsuario(rut);
    }

    // 4. Actualizar usuario
    @PutMapping("/{rut}")
    public Usuario actualizarUsuario(@PathVariable String rut, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(rut, usuario);
    }

    // 5. Eliminar usuario
    @DeleteMapping("/{rut}")
    public String eliminarUsuario(@PathVariable String rut) {
        return usuarioService.deleteUsuario(rut);
    }

    // 6. Cambiar rol de usuario
    @PatchMapping("/{rut}/rol")
    public Usuario cambiarRolUsuario(@PathVariable String rut, @RequestParam String nuevoRol) {
        return usuarioService.cambiarRol(rut, nuevoRol);
    }

    // 7. Bloquear usuario
    @PatchMapping("/{rut}/bloquear")
    public Usuario bloquearUsuario(@PathVariable String rut) {
        return usuarioService.bloquearUsuario(rut);
    }

    // 8. Desbloquear usuario
    @PatchMapping("/{rut}/desbloquear")
    public Usuario desbloquearUsuario(@PathVariable String rut) {
        return usuarioService.desbloquearUsuario(rut);
    }
}
