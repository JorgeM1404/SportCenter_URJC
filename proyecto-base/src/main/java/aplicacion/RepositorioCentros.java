package aplicacion;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames="cacheURJC")
@Repository
public interface RepositorioCentros extends JpaRepository<CentroDeportivo,String>
{
	//public Optional<CentroDeportivo> findByCampus(String campus);
	//public void deleteByCampus(String campus);
	@Cacheable
	Optional<CentroDeportivo> findById(String id);
}
