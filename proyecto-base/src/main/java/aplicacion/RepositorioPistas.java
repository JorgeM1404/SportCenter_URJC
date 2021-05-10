package aplicacion;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames="cacheURJC")
@Repository
public interface RepositorioPistas extends JpaRepository<PistaDeportiva,Long>
{
	@Cacheable
	Optional<PistaDeportiva> findByPista(String pista);
	
	@CacheEvict(allEntries=true)
	void removeByPista(String pista);
}
