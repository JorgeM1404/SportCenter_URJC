package serviciointerno;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioActividades 
{
	@Autowired
	private RepositorioActividades repositorio;
	
	public Collection<Actividad> getActividadesTodas() {
		return repositorio.findAll();
	}
	
	public Actividad getActividadById(long id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	public Actividad getActividadByNombre(String nombre) {
		return repositorio.findByNombre(nombre).orElseThrow();
	}
	
	public void borrarActividadById(long id) {
		Actividad act = repositorio.findById(id).orElseThrow();
		repositorio.delete(act);
	}
	
	public void borrarActividadByNombre(String nombre) {
		Actividad act = repositorio.findByNombre(nombre).orElseThrow();
		repositorio.delete(act);
	}
	
	public void borrarActividad(Actividad act) {
		repositorio.delete(act);
	}
	
	public void guardarActividad(Actividad act) {
		repositorio.save(act);
	}
	
	public void guardarActividades(List<Actividad> act) {
		repositorio.saveAll(act);
	}
}
