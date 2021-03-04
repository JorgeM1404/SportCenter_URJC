package aplicacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario,Long>
{
	Optional<Usuario> findByNombreAndClaveAndCorreo(String nombre, String clave, String correo);
	boolean existsByNombreAndClaveAndCorreo(String nombre, String clave, String correo);
}
