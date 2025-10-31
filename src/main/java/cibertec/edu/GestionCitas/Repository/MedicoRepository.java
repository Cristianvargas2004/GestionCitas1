package cibertec.edu.GestionCitas.Repository;

import cibertec.edu.GestionCitas.Entity.Medico;
import org.springframework.data.repository.CrudRepository;

public interface MedicoRepository extends CrudRepository<Medico, Long> {
    Boolean existsByEmail(String Email);
    Medico findByUsuarioId(Long Id);
}
