package aplicacion;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="cacheURJC")
@Service
public class ServicioPistas 
{
	@Autowired RepositorioPistas repositorio;
	
	@Cacheable
	public Collection<PistaDeportiva> getPistas() {
		return repositorio.findAll();
	}
	
	@Cacheable
	public PistaDeportiva getPistaByNombre(String nombre) {
		return repositorio.findByPista(nombre).orElseThrow();
	}
	
	@CacheEvict(allEntries = true)
	public void borrarPistaByNombre(String nombre) {
		PistaDeportiva pd = repositorio.findByPista(nombre).orElseThrow();
		repositorio.delete(pd);
	}
	
	@Cacheable
	public PistaDeportiva getPistaById(long id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	@CacheEvict(allEntries = true)
	public void borrarPistaById(long id) {
		PistaDeportiva pd = repositorio.findById(id).orElseThrow();
		repositorio.delete(pd);
	}
	
	@CacheEvict(allEntries = true)
	public void guardarPista(PistaDeportiva pd) {
		repositorio.save(pd);
	}	
	
	@CacheEvict(allEntries = true)
	public void guardarPistas(List<PistaDeportiva> pds) {
		repositorio.saveAll(pds);
	}
}
