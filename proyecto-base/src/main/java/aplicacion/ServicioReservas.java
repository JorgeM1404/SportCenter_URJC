package aplicacion;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioReservas 
{
	@Autowired
	private RepositorioReservas repositorio;
	
	public Collection<Reserva> getReservas() {
		return repositorio.findAll();
	}
	
	public Reserva getReservaById(long id) {
		return repositorio.findById(id).orElseThrow();
	}	
	public Reserva getReservaByFecha(Date fecha) {
		return repositorio.findByFecha(fecha).orElseThrow();
	}
	
	public void cancelarReserva(Reserva reserva) {
		repositorio.delete(reserva);
	}	
	public void cancelarReservaByFecha(Date fecha) {
		repositorio.deleteByFecha(fecha);
	}
	public void cancelarReservaById(long id) {
		repositorio.deleteById(id);
	}
	public void cancelarTodas() {
		repositorio.deleteAll();
	}
	
	public void guardarReserva(Reserva res) {
		repositorio.save(res);
	}
	public void guardarReservas(List<Reserva> reservas) {
		repositorio.saveAll(reservas);
	}
	
	public boolean existeReserva(Reserva res) {
		return repositorio.existsById(res.getId());
	}
}
