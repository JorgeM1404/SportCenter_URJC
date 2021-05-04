package aplicacion;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="cacheURJC")
@Service
public class ServicioReservas 
{
	@Autowired
	private RepositorioReservas repositorio;
	
	@Cacheable
	public Collection<Reserva> getReservas() {
		return repositorio.findAll();
	}
	
	@Cacheable
	public Reserva getReservaById(long id) {
		return repositorio.findById(id).orElseThrow();
	}	
	
	@Cacheable
	public Reserva getReservaByFecha(Date fecha) {
		return repositorio.findByFecha(fecha).orElseThrow();
	}
	
	@CacheEvict(allEntries = true)
	public void cancelarReserva(Reserva reserva) {
		repositorio.delete(reserva);
	}	
	@CacheEvict(allEntries = true)
	public void cancelarReservaByFecha(Date fecha) {
		repositorio.deleteByFecha(fecha);
	}
	@CacheEvict(allEntries = true)
	public void cancelarReservaById(long id) {
		repositorio.deleteById(id);
	}
	@CacheEvict(allEntries = true)
	public void cancelarTodas() {
		repositorio.deleteAll();
	}
	
	@CacheEvict(allEntries = true)
	public void guardarReserva(Reserva res) {
		repositorio.save(res);
	}
	@CacheEvict(allEntries = true)
	public void guardarReservas(List<Reserva> reservas) {
		repositorio.saveAll(reservas);
	}
	
	@Cacheable
	public boolean existeReserva(Reserva res) {
		return repositorio.existsById(res.getId());
	}
}
