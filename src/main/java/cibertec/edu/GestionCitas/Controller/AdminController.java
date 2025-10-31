package cibertec.edu.GestionCitas.Controller;

import cibertec.edu.GestionCitas.Entity.DTO.*;
import cibertec.edu.GestionCitas.Entity.*;
import cibertec.edu.GestionCitas.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String MostrarPanel(Model model) {
        return "Admin/Panel";
    }

    @GetMapping("/Usuarios")
    public String ListarUsuarios(HttpSession httpSession, Model model) {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        if (usuario == null) {
            return "Auth/Acceder :: accederFragment";
        }
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "Admin/Usuarios :: usuariosFragment";
    }

    @GetMapping("/FormUsuario")
    public String MostrarFormUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Admin/FormUsuario :: formUsuarioFragment";
    }

    @PostMapping("/CrearUsuario")
    public String CrearUsuario(@ModelAttribute Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        if (!usuarioService.validarUsuario(usuario, model, false)) {
            return "Admin/FormUsuario :: formUsuarioFragment";
        }
        usuario.setRol(Usuario.RolType.Admin);
        usuarioService.crearUsuario(usuario);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "Admin/Usuarios :: usuariosFragment";
    }

    @GetMapping("/EditarUsuario/{id}")
    public String MostrarEditarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("isEdit", true);
        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "Admin/FormUsuario :: formUsuarioFragment";
    }

    @PostMapping("/EditarUsuario")
    public String EditarUsuario(@ModelAttribute Usuario usuario, Model model) {
        model.addAttribute("isEdit", true);
        if (!usuarioService.validarUsuario(usuario, model, true)) {
            return "Admin/FormUsuario :: formUsuarioFragment";
        }
        usuarioService.actualizarUsuario(usuario);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "Admin/Usuarios :: usuariosFragment";
    }

    @PostMapping("/EliminarUsuario/{id}")
    public String EliminarUsuario(@PathVariable Long id, Model model) {
        Boolean deleted = usuarioService.elminarUsuario(id);

        if (deleted) {
            model.addAttribute("eliminado", "Se eliminó el usuario");
        } else {
            model.addAttribute("error", "Error al eliminar el usuario");
        }

        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "Admin/Usuarios :: usuariosFragment";
    }

    @GetMapping("/Medicos")
    public String ListarMedicos(HttpSession httpSession, Model model) {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        if (usuario == null) {
            return "Auth/Acceder :: accederFragment";
        }
        model.addAttribute("medicos", medicoService.listarMedicos());
        return "Admin/Medicos :: medicosFragment";
    }

    @GetMapping("/FormMedico")
    public String MostrarFormMedico(Model model) {
        model.addAttribute("medico", new MedicoUsuarioDTO());
        return "Admin/FormMedico :: formMedicoFragment";
    }

    @PostMapping("/CrearMedico")
    public String CrearMedico(@ModelAttribute MedicoUsuarioDTO medicoUsuarioDTO, Model model) {
        model.addAttribute("medico", medicoUsuarioDTO);

        Usuario usuario = new Usuario();
        usuario.setNombre(medicoUsuarioDTO.getNombre());
        usuario.setEmail(medicoUsuarioDTO.getEmail());
        usuario.setRol(Usuario.RolType.Medico);
        usuario.setUsername(medicoUsuarioDTO.getUsername());
        usuario.setPassword(medicoUsuarioDTO.getPassword());

        if (!usuarioService.validarUsuario(usuario, model, false)) {
            model.addAttribute("medico", medicoUsuarioDTO);
            return "Admin/FormMedico :: formMedicoFragment";
        }

        Medico medico = new Medico();
        medico.setNombre(medicoUsuarioDTO.getNombre());
        medico.setEmail(medicoUsuarioDTO.getEmail());
        medico.setTelefono(medicoUsuarioDTO.getTelefono());
        medico.setEspecialidad(medicoUsuarioDTO.getEspecialidad());
        medico.setUsuario(usuario);

        usuarioService.crearUsuario(usuario);
        medicoService.crearMedico(medico);

        model.addAttribute("medicos", medicoService.listarMedicos());
        return "Admin/Medicos :: medicosFragment";
    }

    @GetMapping("/EditarMedico/{Id}")
    public String MostrarEditarMedico(@PathVariable Long Id, Model model) {
        Medico medico = medicoService.obtenerPorId(Id);
        Usuario usuario = usuarioService.obtenerPorId(medico.getUsuario().getId());
        MedicoUsuarioDTO medicoUsuarioDTO = new MedicoUsuarioDTO(
                medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad(),
                medico.getTelefono(),
                medico.getEmail(),
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword());
        if (!usuarioService.validarUsuario(usuario, model, true)) {
            model.addAttribute("medico", medicoUsuarioDTO);
            return "Admin/FormMedico :: formMedicoFragment";
        }
        model.addAttribute("isEdit", true);
        model.addAttribute("medico", medicoUsuarioDTO);
        return "Admin/FormMedico :: formMedicoFragment";
    }

    @PostMapping("/EditarMedico")
    public String EditarMedico(@ModelAttribute MedicoUsuarioDTO medicoUsuarioDTO, Model model) {
        Usuario usuario = new Usuario(
                medicoUsuarioDTO.getUsuarioId(),
                medicoUsuarioDTO.getNombre(),
                medicoUsuarioDTO.getEmail(),
                Usuario.RolType.Medico,
                medicoUsuarioDTO.getUsername(),
                medicoUsuarioDTO.getPassword());

        if (!usuarioService.validarUsuario(usuario, model, true)) {
            model.addAttribute("medico", medicoUsuarioDTO);
            return "Admin/FormMedico :: formMedicoFragment";
        }

        Medico medico = new Medico(
                medicoUsuarioDTO.getId(),
                medicoUsuarioDTO.getNombre(),
                medicoUsuarioDTO.getEspecialidad(),
                medicoUsuarioDTO.getTelefono(),
                medicoUsuarioDTO.getEmail(),
                usuario);

        usuarioService.actualizarUsuario(usuario);
        medicoService.actualizarMedico(medico);

        model.addAttribute("medicos", medicoService.listarMedicos());
        return "Admin/Medicos :: medicosFragment";
    }

    @PostMapping("/EliminarMedico/{id}")
    public String EliminarMedico(@PathVariable Long id, Model model) {
        Medico medico = medicoService.obtenerPorId(id);
        Boolean deleted = usuarioService.elminarUsuario(medico.getUsuario().getId());
        medicoService.eliminarMedico(id);
        if (deleted) {
            model.addAttribute("eliminado", "Se eliminó el médico");
        } else {
            model.addAttribute("error", "Error al eliminar el médico");
        }

        model.addAttribute("medicos", medicoService.listarMedicos());
        return "Admin/Medicos :: medicosFragment";
    }

    @GetMapping("/Pacientes")
    public String ListarPacientes(HttpSession httpSession, Model model) {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        if (usuario == null) {
            return "Auth/Acceder :: accederFragment";
        }
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "Admin/Pacientes :: pacientesFragment";
    }

    @GetMapping("/EditarPaciente/{id}")
    public String MostrarEditarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.obtenerPorId(id);
        Usuario usuario = usuarioService.obtenerPorId(paciente.getUsuario().getId());

        PacienteUsuarioDTO pacienteUsuarioDTO = new PacienteUsuarioDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellidos(),
                paciente.getDni(),
                paciente.getEmail(),
                paciente.getTelefono(),
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword());

        model.addAttribute("paciente", pacienteUsuarioDTO);

        return "Admin/EditarPaciente :: editarPacienteFragment";
    }

    @PostMapping("/EditarPaciente")
    public String EditarPaciente(@ModelAttribute PacienteUsuarioDTO pacienteUsuarioDTO, Model model) {
        Usuario usuario = new Usuario(
                pacienteUsuarioDTO.getUsuarioId(),
                pacienteUsuarioDTO.getNombre(),
                pacienteUsuarioDTO.getEmail(),
                Usuario.RolType.Paciente,
                pacienteUsuarioDTO.getUsername(),
                pacienteUsuarioDTO.getPassword());

        if (!usuarioService.validarUsuario(usuario, model, true)) {
            model.addAttribute("paciente", pacienteUsuarioDTO);
            return "Admin/EditarPaciente :: editarPacienteFragment";
        }

        Paciente paciente = new Paciente(
                pacienteUsuarioDTO.getId(),
                pacienteUsuarioDTO.getNombre(),
                pacienteUsuarioDTO.getApellidos(),
                pacienteUsuarioDTO.getDni(),
                pacienteUsuarioDTO.getEmail(),
                pacienteUsuarioDTO.getTelefono(),
                pacienteUsuarioDTO.getUsername(),
                usuario);
        usuarioService.actualizarUsuario(usuario);
        pacienteService.actualizarPaciente(paciente);
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "Admin/Pacientes :: pacientesFragment";
    }

    @PostMapping("EliminarPaciente/{id}")
    public String ElminarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.obtenerPorId(id);
        Boolean deleted = usuarioService.elminarUsuario(paciente.getUsuario().getId());
        pacienteService.eliminarPaciente(id);
        if (deleted) {
            model.addAttribute("eliminado", "Se eliminó el paciente");
        } else {
            model.addAttribute("error", "Error al eliminar el paciente");
        }
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "Admin/Pacientes :: pacientesFragment";
    }

}
