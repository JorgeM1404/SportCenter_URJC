package aplicacion;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="cacheURJC")
@Service
public class ServicioActividades 
{
	@Autowired
	private RepositorioActividades repositorio;
	
	@Cacheable
	public Collection<Actividad> getActividadesTodas() {
		return repositorio.findAll();
	}
	
	@Cacheable
	public Actividad getActividadById(long id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	@Cacheable
	public Actividad getActividadByNombre(String nombre) {
		return repositorio.findByNombre(nombre).orElseThrow();
	}
	
	@CacheEvict(allEntries=true)
	public void borrarActividadById(long id) {
		Actividad act = repositorio.findById(id).orElseThrow();
		repositorio.delete(act);
	}
	
	@CacheEvict(allEntries=true)
	public void borrarActividadByNombre(String nombre) {
		Actividad act = repositorio.findByNombre(nombre).orElseThrow();
		repositorio.delete(act);
	}
	
	@CacheEvict(allEntries=true)
	public void borrarActividad(Actividad act) {
		repositorio.delete(act);
	}
	
	@CacheEvict(allEntries=true)
	public void guardarActividad(Actividad act) {
		repositorio.save(act);
	}
	
	@CacheEvict(allEntries=true)
	public void guardarActividades(List<Actividad> act) {
		repositorio.saveAll(act);
	}
}
