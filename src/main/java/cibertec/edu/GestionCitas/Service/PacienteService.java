package cibertec.edu.GestionCitas.Service;

import cibertec.edu.GestionCitas.Entity.Paciente;
import cibertec.edu.GestionCitas.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crearPaciente(Paciente paciente) {
        if (existePorEmail(paciente.getEmail())) {
            throw new IllegalArgumentException("El Email ya está registrado");
        }
        if (existePorUsername(paciente.getUsername())) {
            throw new IllegalArgumentException("El Username ya está registrado");
        }
        if (existePorDni(paciente.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        Optional<Paciente> objPaciente = pacienteRepository.findById(paciente.getId());
        if(objPaciente.isEmpty()) {
            throw new IllegalArgumentException("Paciente no valido.");
        }
        Paciente pacienteActualizado = objPaciente.get();
        pacienteActualizado.setNombre(paciente.getNombre());
        pacienteActualizado.setApellidos(paciente.getApellidos());
        pacienteActualizado.setTelefono(paciente.getTelefono());
        pacienteActualizado.setEmail(paciente.getEmail());

        return pacienteRepository.save(pacienteActualizado);
    }

    public List<Paciente> listarPacientes() {
        return (List<Paciente>) pacienteRepository.findAll();
    }

    public Boolean eliminarPaciente(Long id) {
        if(pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Paciente obtenerPorId(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente obtenerPorUsuarioId(Long Id) {
        return pacienteRepository.findByUsuarioId(Id);
    }

    public Boolean existePorEmail(String Email) {
        return pacienteRepository.existsByEmail(Email);
    }

    public Boolean existePorUsername(String Username) {
        return pacienteRepository.existsByUsername(Username);
    }

    public Boolean existePorDni(String Dni) {
        return pacienteRepository.existsByDni(Dni);
    }

}
