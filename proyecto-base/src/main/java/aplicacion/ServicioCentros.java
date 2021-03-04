package aplicacion;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCentros 
{
	@Autowired
	private RepositorioCentros repositorio;
	
	@Autowired
	private ServicioActividades servicioActividades;
	
	@PostConstruct
	public void Init()
	{   
		CentroDeportivo centro1 = new CentroDeportivo("Mostoles");		
	    repositorio.save(centro1);
	    setActividadesMostoles(centro1);
	   
	    repositorio.save(new CentroDeportivo ("Alcorcón"));
	    repositorio.save(new CentroDeportivo ("Fuenlabrada"));
	    repositorio.save(new CentroDeportivo ("Aranjuez"));
	    repositorio.save(new CentroDeportivo ("Vicálvaro"));
	}
	
	public void setActividadesMostoles(CentroDeportivo centro)
	{		
		Actividad act1 = new Actividad("Football");  act1.getCentros().add(centro);
		Actividad act2 = new Actividad("Baloncesto");  act2.getCentros().add(centro);
		Actividad act3 = new Actividad("Tenis");  act3.getCentros().add(centro);
		Actividad act4 = new Actividad("Balonmano");  act4.getCentros().add(centro);
		Actividad act5 = new Actividad("Natacion");  act5.getCentros().add(centro);

		servicioActividades.guardarActividad(act1);
		servicioActividades.guardarActividad(act2);
		servicioActividades.guardarActividad(act3);
		servicioActividades.guardarActividad(act4);
		servicioActividades.guardarActividad(act5);
	}
	
	public List<Actividad> getActividadesCentro(String id) {
		return repositorio.findById(id).orElseThrow().getActividades();
	}
	
	public CentroDeportivo getCentro(String id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	public void borrarCentro(String id) {
		repositorio.deleteById(id);
	}
	
	public Collection<CentroDeportivo> findAll() {
		return repositorio.findAll();
	}
}
