package cibertec.edu.GestionCitas.Controller;

import cibertec.edu.GestionCitas.Entity.Usuario;
import cibertec.edu.GestionCitas.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String Home() {
        if (usuarioService.obtenerPorUsername("admin") == null) {
            insert();
        }
        return "redirect:/Auth";
    }

    public Usuario insert() {
        // Cargamos un usuario Admin
        Usuario usuario = new Usuario();
        usuario.setNombre("Admin");
        usuario.setEmail("admin@gmail.com");
        usuario.setUsername("admin");
        usuario.setPassword("admin123");
        usuario.setRol(Usuario.RolType.Admin);
        return usuarioService.crearUsuario(usuario);
    }
}
