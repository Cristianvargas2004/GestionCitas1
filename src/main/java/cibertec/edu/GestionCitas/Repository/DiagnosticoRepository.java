package cibertec.edu.GestionCitas.Repository;

import cibertec.edu.GestionCitas.Entity.Diagnostico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Long> {
    List<Diagnostico> findByPacienteId(Long Id);

    List<Diagnostico> findByMedicoId(Long Id);
}
