package cibertec.edu.GestionCitas.Repository;

import cibertec.edu.GestionCitas.Entity.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    Boolean existsByDni(String Dni);
    Boolean existsByEmail(String Email);
    Boolean existsByUsername(String Username);
    Paciente findByUsuarioId(Long Id);
}
