package cibertec.edu.GestionCitas.Repository;

import cibertec.edu.GestionCitas.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Boolean existsByUsername(String Username);
    Boolean existsByEmail(String Email);
    Usuario findByUsernameAndPassword(String username, String password);
    Usuario findByEmail(String Email);
    Usuario findByUsername(String username);
}
