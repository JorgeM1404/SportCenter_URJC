package aplicacion;

import java.util.Collection;
import java.util.Optional;
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
	
	public Optional<Actividad> getActividad(String id) {
		return repositorio.findById(id);
	}
	
	public void borrarUsuarios(String id) {
		Actividad act = repositorio.findById(id).orElseThrow();
		repositorio.delete(act);
	}
	
	public void guardarActividad(Actividad act) {
		repositorio.save(act);
	}
}
