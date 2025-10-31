package cibertec.edu.GestionCitas.Service;

import cibertec.edu.GestionCitas.Entity.Medico;
import cibertec.edu.GestionCitas.Repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico crearMedico(Medico medico) {
        if (existePorEmail(medico.getEmail())) {
            throw new IllegalArgumentException("El Email ya está registrado");
        }
        if (medico.getUsuario().getId() == null) {
            throw new IllegalArgumentException("Necesitas el id del usuario");
        }
        return medicoRepository.save(medico);
    }

    public List<Medico> listarMedicos() {
        return (List<Medico>) medicoRepository.findAll();
    }

    public Medico actualizarMedico(Medico medico) {
        Optional<Medico> objMedico = medicoRepository.findById(medico.getId());
        if (objMedico.isEmpty()) {
            throw new IllegalArgumentException("Médico no existente.");
        }
        Medico medicoActualizado = objMedico.get();
        medicoActualizado.setNombre(medico.getNombre());
        medicoActualizado.setEmail(medicoActualizado.getEmail());
        medicoActualizado.setTelefono(medico.getTelefono());
        return medicoRepository.save(medicoActualizado);
    }

    public Boolean eliminarMedico(Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Boolean existePorEmail(String Email) {
        return medicoRepository.existsByEmail(Email);
    }

    public Medico obtenerPorUsuarioId(Long Id) {
        return medicoRepository.findByUsuarioId(Id);
    }

    public Medico obtenerPorId(Long Id) {
        Optional<Medico> medico = medicoRepository.findById(Id);
        if (medico.isEmpty()) {
            throw new IllegalArgumentException("No existe");
        }
        return medico.get();
    }

}
