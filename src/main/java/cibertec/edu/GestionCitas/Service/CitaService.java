package cibertec.edu.GestionCitas.Service;

import cibertec.edu.GestionCitas.Entity.*;
import cibertec.edu.GestionCitas.Repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public Cita crearCita(Cita cita, Paciente paciente) {
        cita.setEstado(Cita.EstadoCita.Pendiente);
        cita.setPaciente(paciente);
        return citaRepository.save(cita);
    }

    public Cita actualizarCita(Cita cita) {
        Cita citaActualizada = obtenerPorId(cita.getId());
        citaActualizada.setFecha(cita.getFecha());
        citaActualizada.setHora(cita.getHora());
        citaActualizada.setEstado(cita.getEstado());
        return citaRepository.save(citaActualizada);
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public List<Paciente> listarPacientesPorMedicoId(Long Id) {
        return citaRepository.findPacienteByMedicoId(Id);
    }

    public List<Cita> listarPorPacienteId(Long Id) {
        return citaRepository.findByPacienteId(Id);
    }

    public List<Cita> listarPorMedicoId(Long Id) {
        return citaRepository.findByMedicoId(Id);
    }

}
