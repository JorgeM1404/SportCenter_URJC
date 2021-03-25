package aplicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario,Long>
{
	Usuario findByNombre(String nombre);
	boolean existsByNombreAndPasswordHashAndCorreo(String nombre, String passwordHash, String correo);
	Usuario findByNombreAndPasswordHashAndCorreo(String nombre, String passwordHash, String correo);
}
