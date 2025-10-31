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
@RequestMapping("/Medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private CitaService citaService;
    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping()
    public String MostrarPanel(HttpSession httpSession, Model model) {
        Medico medico = (Medico) httpSession.getAttribute("medico");
        model.addAttribute("medico", medico);
        return "Medico/Panel";
    }

    @GetMapping("/CitasMedico")
    public String CitasMedico(HttpSession httpSession, Model model) {
        Medico medico = (Medico) httpSession.getAttribute("medico");
        if (medico == null) {
            return "Auth/Acceder :: accederFragment";
        }
        List<Cita> citas = citaService.listarPorMedicoId(medico.getId());
        model.addAttribute("citas", citas);
        return "Medico/CitasMedico :: citasFragment";
    }

    @GetMapping("/EditarCitas/{id}")
    public String MostrarEditarCita(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtenerPorId(id);
        System.out.println(cita.getFecha());
        model.addAttribute("cita", cita);
        return "Medico/EditarCitas :: editarCitaFragment";
    }

    @PostMapping("/EditarCita")
    public String EditarCita(@ModelAttribute Cita cita, HttpSession httpSession, Model model) {
        citaService.actualizarCita(cita);
        model.addAttribute("mensaje", "Cita actualizada correctamente");
        Medico medico = (Medico) httpSession.getAttribute("medico");
        if (medico == null) {
            return "Auth/Acceder :: accederFragment";
        }
        model.addAttribute("citas", citaService.listarPorMedicoId(medico.getId()));
        return "Medico/CitasMedico :: citasFragment";
    }

    @GetMapping("/Diagnosticos")
    public String Diagnosticos(HttpSession httpSession, Model model) {
        Medico medico = (Medico) httpSession.getAttribute("medico");
        if (medico == null) {
            return "Auth/Acceder :: accederFragment";
        }
        List<Diagnostico> historiales = diagnosticoService.listarPorMedicoId(medico.getId());
        model.addAttribute("diagnosticos", historiales);
        return "Medico/DiagnosticoMedico :: diagnosticosFragment";
    }

    @GetMapping("/RegistrarDiagnostico")
    public String MostrarRegistrarDiagnostico(HttpSession httpSession, Model model) {
        Medico medico = (Medico) httpSession.getAttribute("medico");
        List<Paciente> pacientes = citaService.listarPacientesPorMedicoId(medico.getId());
        if (pacientes.isEmpty()) {
            model.addAttribute("mensaje", "No tienes ningún paciente");
            return "Medico/DiagnosticoMedico :: diagnosticosFragment";
        }
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("form", new Diagnostico());
        return "Medico/RegistrarDiagnostico :: registrarDiagnosticoFragment";
    }

    @PostMapping("/RegistrarDiagnostico")
    public String RegistrarDiagnostico(@ModelAttribute("form") Diagnostico form, HttpSession httpSession, Model model) {
        Medico medico = (Medico) httpSession.getAttribute("medico");
        form.setMedico(medico);
        diagnosticoService.crearDiagnostico(form);
        List<Diagnostico> historiales = diagnosticoService.listarPorMedicoId(medico.getId());
        model.addAttribute("diagnosticos", historiales);
        return "Medico/DiagnosticoMedico :: diagnosticosFragment";
    }

    @GetMapping("/PerfilMedico")
    public String MostrarPerfilMedico(HttpSession httpSession, Model model) {
        Medico medico = (Medico) httpSession.getAttribute("medico");
        if (medico == null) {
            return "Auth/Acceder :: accederFragment";
        }
        model.addAttribute("medico", medico);
        return "Medico/PerfilMedico :: perfilMedicoFragment";
    }

    @PostMapping("/ActualizarPerfil")
    public String ActualizarPerfil(@ModelAttribute Medico medico, HttpSession httpSession, Model model) {
        Medico medicoActualizado = medicoService.actualizarMedico(medico);
        httpSession.setAttribute("medico", medicoActualizado);
        model.addAttribute("mensaje", "Actualizado correctamente");
        return "Medico/PerfilMedico :: perfilMedicoFragment";
    }
}
