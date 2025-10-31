package cibertec.edu.GestionCitas.Service;

import cibertec.edu.GestionCitas.Entity.Usuario;
import cibertec.edu.GestionCitas.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        if (existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El Email ya está registrado");
        }
        if (existePorUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El Username ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        Optional<Usuario> obj = usuarioRepository.findById(usuario.getId());
        if (obj.isEmpty()) {
            throw new IllegalArgumentException("No existe");
        }
        Usuario usuarioActualizado;
        usuarioActualizado = usuario;

        return usuarioRepository.save(usuarioActualizado);
    }

    public Boolean elminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Usuario obtenerPorId(Long Id) {
        Optional<Usuario> usuario = usuarioRepository.findById(Id);
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("No existe");
        }
        return usuario.get();
    }

    public Usuario obtenerPorEmail(String Email) {
        return usuarioRepository.findByEmail(Email);
    }

    public Usuario obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario iniciarSesion(String username, String password) {
        return usuarioRepository.findByUsernameAndPassword(username, password);
    }

    public Boolean existePorEmail(String Email) {
        return usuarioRepository.existsByEmail(Email);
    }

    public Boolean existePorUsername(String Username) {
        return usuarioRepository.existsByUsername(Username);
    }

    public Boolean validarUsuario(Usuario usuario, Model model, Boolean isEdit) {
        if (isEdit) {
            Usuario actual = obtenerPorId(usuario.getId());

            Usuario otroConMismoEmail = obtenerPorEmail(usuario.getEmail());
            if (otroConMismoEmail != null && !otroConMismoEmail.getId().equals(actual.getId())) {
                model.addAttribute("isEdit", true);
                model.addAttribute("mensaje", "El email ya está registrado");
                return false;
            }

            Usuario otroConMismoUsername = obtenerPorUsername(usuario.getUsername());
            if (otroConMismoUsername != null && !otroConMismoUsername.getId().equals(actual.getId())) {
                model.addAttribute("isEdit", true);
                model.addAttribute("mensaje", "El usuario ya está registrado");
                return false;
            }

            return true;
        } else {
            if (existePorEmail(usuario.getEmail())) {
                model.addAttribute("mensaje", "El email ya está registrado");
                return false;
            }

            if (existePorUsername(usuario.getUsername())) {
                model.addAttribute("mensaje", "El usuario ya está registrado");
                return false;
            }

            return true;
        }
    }
}
