package cibertec.edu.GestionCitas.Controller;

import cibertec.edu.GestionCitas.Entity.DTO.PacienteUsuarioDTO;
import cibertec.edu.GestionCitas.Entity.*;
import cibertec.edu.GestionCitas.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Auth")
public class AuthController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"", "/Acceder"})
    public String MostrarAcceder() {
        return "Auth/Acceder";
    }

    @PostMapping("/Acceder")
    public String Acceder(@RequestParam String username, @RequestParam String password, Model model, HttpSession httpSession) {
        Usuario usuario = usuarioService.iniciarSesion(username, password);
        if (usuario == null) {
            model.addAttribute("UsuarioNoEncontrado", "Credenciales Incorrectas");
            return "Auth/Acceder";
        }
        switch (usuario.getRol()) {
            case Admin:
                httpSession.setAttribute("usuario", usuario);
                return "redirect:/Admin";
            case Medico:
                Medico medico = medicoService.obtenerPorUsuarioId(usuario.getId());
                httpSession.setAttribute("medico", medico);
                return "redirect:/Medico";
            case Paciente:
                Paciente paciente = pacienteService.obtenerPorUsuarioId(usuario.getId());
                httpSession.setAttribute("paciente", paciente);
                return "redirect:/Paciente/Panel";
        }
        return "Auth/Acceder";
    }

    @GetMapping("/Registrar")
    public String Registrar(Model model) {
        model.addAttribute("paciente", new PacienteUsuarioDTO());
        return "Auth/Registrar";
    }

    @PostMapping("/Registrar")
    public String Registrar(@ModelAttribute PacienteUsuarioDTO pacienteUsuarioDTO, Model model) {
        model.addAttribute("paciente", pacienteUsuarioDTO);
        if(usuarioService.existePorEmail(pacienteUsuarioDTO.getEmail())) {
            model.addAttribute("mensaje", "El email ya está registrado");
            return "Auth/Registrar";
        } else if (usuarioService.existePorUsername(pacienteUsuarioDTO.getUsername())) {
            model.addAttribute("mensaje", "El usuario ya está registrado");
            return "Auth/Registrar";
        } else if (pacienteService.existePorDni(pacienteUsuarioDTO.getDni())) {
            model.addAttribute("mensaje", "El DNI ya está registrado");
            return "Auth/Registrar";
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(pacienteUsuarioDTO.getNombre());
        usuario.setEmail(pacienteUsuarioDTO.getEmail());
        usuario.setRol(Usuario.RolType.Paciente);
        usuario.setUsername(pacienteUsuarioDTO.getUsername());
        usuario.setPassword(pacienteUsuarioDTO.getPassword());
        usuarioService.crearUsuario(usuario);
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteUsuarioDTO.getNombre());
        paciente.setDni(pacienteUsuarioDTO.getDni());
        paciente.setApellidos(pacienteUsuarioDTO.getApellidos());
        paciente.setEmail(pacienteUsuarioDTO.getApellidos());
        paciente.setTelefono(pacienteUsuarioDTO.getTelefono());
        paciente.setUsername(pacienteUsuarioDTO.getUsername());
        paciente.setUsuario(usuario);
        pacienteService.crearPaciente(paciente);

        model.addAttribute("registroExitoso", "Inicia Sesión");

        return "Auth/Acceder";
    }

    @GetMapping("/Salir")
    public String Salir(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/Auth";
    }
}
