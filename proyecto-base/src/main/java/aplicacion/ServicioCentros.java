package aplicacion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="cacheURJC")
@Service
public class ServicioCentros 
{
	@Autowired
	private RepositorioCentros repositorio;	
	
	@Cacheable
	public List<Actividad> getActividadesCentro(String id) {
		return repositorio.findById(id).orElseThrow().getActividades();
	}
	
	@Cacheable
	public CentroDeportivo getCentro(String id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	@CacheEvict(allEntries = true)
	public void borrarCentro(String id) {
		repositorio.deleteById(id);
	}
	
	@Cacheable
	public List<CentroDeportivo> findAll() {
		return repositorio.findAll();
	}
}
