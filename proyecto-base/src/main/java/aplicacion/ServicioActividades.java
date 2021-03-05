package aplicacion;

import java.util.Collection;
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
	
	public Actividad getActividad(String id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	public void borrarUsuarios(String id) {
		Actividad act = repositorio.findById(id).orElseThrow();
		repositorio.delete(act);
	}
	
	public void guardarActividad(Actividad act) {
		repositorio.save(act);
	}
}
