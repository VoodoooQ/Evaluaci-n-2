package controller;

import model.Usuario;
import service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Usuarios1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("{rut}")
    public Usuario buscarUsuario(@PathVariable int rut) {
        return usuarioService.getUsuario(rut);
    }

    @PutMapping("rut")
    public Usuario actualizarUsuario(@PathVariable int rut, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(rut, usuario);
    }

    @DeleteMapping("{rut}")
    public String eliminarUsuario(@PathVariable int rut) {
        return usuarioService.deleteUsuario(rut);
    }
}
