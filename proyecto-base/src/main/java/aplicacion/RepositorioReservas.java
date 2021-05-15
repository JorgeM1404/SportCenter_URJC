package aplicacion;

import java.util.Date;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@CacheConfig(cacheNames="cacheURJC")
@Repository
public interface RepositorioReservas extends JpaRepository<Reserva,Long>
{
	@Cacheable
    Optional<Reserva> findById(long id);
	
	@Cacheable
	Optional<Reserva> findByFecha(Date date);
	
	@CacheEvict(allEntries=true)
	void deleteByFecha(Date date);
}
