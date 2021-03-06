package aplicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario,String>
{
	boolean existsByNombreAndClaveAndCorreo(String nombre, String clave, String correo);
	Usuario findByNombreAndClaveAndCorreo(String nombre, String clave, String correo);
}
