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
	   
	    CentroDeportivo centro2 = new CentroDeportivo("Alcorcón");		
	    repositorio.save(centro2);
	    setActividadesAlcorcon(centro2);
	    
	    CentroDeportivo centro3 = new CentroDeportivo("Fuenlabrada");		
	    repositorio.save(centro3);
	    setActividadesFuenlabrada(centro3);
	    
	    CentroDeportivo centro4 = new CentroDeportivo("Aranjuez");		
	    repositorio.save(centro4);
	    setActividadesAranjuez(centro4);
	    
	    CentroDeportivo centro5 = new CentroDeportivo("Vicálvaro");		
	    repositorio.save(centro5);
	    setActividadesVicalvaro(centro5);
	    
	    
	    //repositorio.save(new CentroDeportivo ("Alcorcón"));
	    //repositorio.save(new CentroDeportivo ("Fuenlabrada"));
	    //repositorio.save(new CentroDeportivo ("Aranjuez"));
	    //repositorio.save(new CentroDeportivo ("Vicálvaro"));
	}
		
	public void setActividadesMostoles(CentroDeportivo centro)
	{		
		Actividad act1 = new Actividad("Football_Mostoles");  act1.getCentros().add(centro);
		Actividad act2 = new Actividad("Baloncesto_Mostoles");  act2.getCentros().add(centro);
		Actividad act3 = new Actividad("Tenis_Mostoles");  act3.getCentros().add(centro);
		Actividad act4 = new Actividad("Natacion_Mostoles");  act4.getCentros().add(centro);
		Actividad act5 = new Actividad("Judo_Mostoles");  act5.getCentros().add(centro);
		Actividad act6 = new Actividad("Padel_Mostoles");  act6.getCentros().add(centro);
		
		servicioActividades.guardarActividad(act1);
		servicioActividades.guardarActividad(act2);
		servicioActividades.guardarActividad(act3);
		servicioActividades.guardarActividad(act4);
		servicioActividades.guardarActividad(act5);
		servicioActividades.guardarActividad(act6);
	}
	
	public void setActividadesAlcorcon(CentroDeportivo centro)
	{		
		Actividad act1 = new Actividad("Football_Alcorcon");  act1.getCentros().add(centro);
		Actividad act2 = new Actividad("Baloncesto_Alcorcon");  act2.getCentros().add(centro);
		Actividad act3 = new Actividad("Balonmano_Alcorcon");  act3.getCentros().add(centro);
		Actividad act4 = new Actividad("Judo_Alcorcon");  act4.getCentros().add(centro);
		Actividad act5 = new Actividad("Padel_Alcorcon");  act5.getCentros().add(centro);

		servicioActividades.guardarActividad(act1);
		servicioActividades.guardarActividad(act2);
		servicioActividades.guardarActividad(act3);
		servicioActividades.guardarActividad(act4);
		servicioActividades.guardarActividad(act5);
		
	}
	
	public void setActividadesFuenlabrada(CentroDeportivo centro)
	{		
		Actividad act1 = new Actividad("Football_Fuenla");  act1.getCentros().add(centro);
		Actividad act2 = new Actividad("Baloncesto_Fuenla");  act2.getCentros().add(centro);
		Actividad act3 = new Actividad("Tenis_Fuenla");  act3.getCentros().add(centro);
		Actividad act4 = new Actividad("Balonmano_Fuenla");  act4.getCentros().add(centro);
		Actividad act5 = new Actividad("Hockey_Fuenla");  act5.getCentros().add(centro);
		Actividad act6 = new Actividad("Judo_Fuenla");  act6.getCentros().add(centro);
		
		servicioActividades.guardarActividad(act1);
		servicioActividades.guardarActividad(act2);
		servicioActividades.guardarActividad(act3);
		servicioActividades.guardarActividad(act4);
		servicioActividades.guardarActividad(act5);
		servicioActividades.guardarActividad(act6);
	}
	
	public void setActividadesAranjuez(CentroDeportivo centro)
	{		
		Actividad act1 = new Actividad("Football_Aranjuez");  act1.getCentros().add(centro);
		Actividad act2 = new Actividad("Baloncesto_Aranjuez");  act2.getCentros().add(centro);
		Actividad act3 = new Actividad("Tenis_Aranjuez");  act3.getCentros().add(centro);
		Actividad act4 = new Actividad("Natacion_Aranjuez");  act4.getCentros().add(centro);
		
		servicioActividades.guardarActividad(act1);
		servicioActividades.guardarActividad(act2);
		servicioActividades.guardarActividad(act3);
		servicioActividades.guardarActividad(act4);
	}
	
	public void setActividadesVicalvaro(CentroDeportivo centro)
	{		
		Actividad act1 = new Actividad("Football_Vicalvaro");  act1.getCentros().add(centro);
		Actividad act2 = new Actividad("Judo_Vicalvaro");  act2.getCentros().add(centro);
		Actividad act3 = new Actividad("Padel_Vicalvaro");  act3.getCentros().add(centro);

		servicioActividades.guardarActividad(act1);
		servicioActividades.guardarActividad(act2);
		servicioActividades.guardarActividad(act3);
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
