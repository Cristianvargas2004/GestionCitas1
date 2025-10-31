package cibertec.edu.GestionCitas.Service;

import cibertec.edu.GestionCitas.Entity.Diagnostico;
import cibertec.edu.GestionCitas.Repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public Diagnostico crearDiagnostico(Diagnostico diagnostico) {
        diagnostico.setFecha(LocalDate.now());
        return diagnosticoRepository.save(diagnostico);
    }

    public List<Diagnostico> listarPorMedicoId(Long Id) {
        return diagnosticoRepository.findByMedicoId(Id);
    }
}
