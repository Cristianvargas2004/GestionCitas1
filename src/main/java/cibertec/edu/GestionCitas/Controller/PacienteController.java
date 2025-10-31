package cibertec.edu.GestionCitas.Controller;

import cibertec.edu.GestionCitas.Entity.*;
import cibertec.edu.GestionCitas.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private CitaService citaService;

    @GetMapping("/Panel")
    public String MostrarPanel(HttpSession httpSession, Model model) {
        return "Paciente/Panel";
    }

    @GetMapping("/ReservarCita")
    public String MostrarReservarCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("medicos", medicoService.listarMedicos());
        return "Paciente/ReservarCita :: reservarCitaFragment";
    }

    @PostMapping("/ReservarCita")
    public String ReservarCita(@ModelAttribute Cita cita, HttpSession httpSession, Model model) {
        Paciente paciente = (Paciente) httpSession.getAttribute("paciente");
        if(paciente == null) {
            return "Auth/Acceder :: accederFragment";
        }
        citaService.crearCita(cita, paciente);
        List<Cita> citas = citaService.listarPorPacienteId(paciente.getId());
        model.addAttribute("citas", citas);
        return "Paciente/MisCitas";
    }

    @GetMapping("/MisCitas")
    public String MostrarMisCitas(HttpSession httpSession, Model model) {
        Paciente paciente = (Paciente) httpSession.getAttribute("paciente");
        if(paciente == null) {
            return "Auth/Acceder :: accederFragment";
        }
        List<Cita> citas = citaService.listarPorPacienteId(paciente.getId());
        model.addAttribute("citas", citas);
        return "Paciente/MisCitas :: citasFragment";
    }

    @GetMapping("/PerfilPaciente")
    public String PerfilPaciente(HttpSession httpSession, Model model) {
        Paciente paciente = (Paciente) httpSession.getAttribute("paciente");
        if(paciente == null) {
            return "Auth/Acceder :: accederFragment";
        }
        model.addAttribute("paciente", paciente);
        return "Paciente/PerfilPaciente :: perfilFragment";
    }

    @PostMapping("/ActualizarPerfil")
    public String ActualizarPerfil(@ModelAttribute Paciente paciente, HttpSession httpSession, Model model) {
        Paciente pacienteActualizado = pacienteService.actualizarPaciente(paciente);
        httpSession.setAttribute("paciente", pacienteActualizado);
        model.addAttribute("mensaje", "Actualizado correctamente");
        return "paciente/PerfilPaciente :: perfilFragment";
    }
}
