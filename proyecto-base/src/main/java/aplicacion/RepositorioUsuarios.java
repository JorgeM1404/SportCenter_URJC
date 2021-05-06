package aplicacion;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames="cacheURJC")
@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario,Long>
{
	@Cacheable
	Optional <Usuario> findByNombre(String nombre);
	@Cacheable
	boolean existsByNombreAndPasswordHashAndCorreo(String nombre, String passwordHash, String correo);
	@Cacheable
	Usuario findByNombreAndPasswordHashAndCorreo(String nombre, String passwordHash, String correo);
}
