package aplicacion;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@CacheConfig(cacheNames="cacheURJC")
@Repository
public interface RepositorioActividades extends JpaRepository<Actividad,Long> {
	@Cacheable
	Optional <Actividad> findByNombre(String nombre);
	//List<Actividad> findByNombre(String nombre);
}
