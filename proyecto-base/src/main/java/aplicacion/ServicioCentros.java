package aplicacion;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
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
	
	@Autowired
	private ServicioPistas servicioPistas;
	
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
	}
		
	public void setActividadesMostoles(CentroDeportivo centro)
	{	
		PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
		Actividad a1 = new Actividad("Football_Mostoles",Arrays.asList(p1));
		p1.setActividad(a1);  a1.getCentros().add(centro);
		
		PistaDeportiva p2 = new PistaDeportiva("Cancha de baloncesto",false);	
		Actividad a2 = new Actividad("Baloncesto_Mostoles", Arrays.asList(p2));
		p2.setActividad(a2);  a2.getCentros().add(centro);
		
		PistaDeportiva p3a = new PistaDeportiva("Pista de tenis A",false);	
		PistaDeportiva p3b = new PistaDeportiva("Pista de tenis B",false);	
		Actividad a3 = new Actividad("Tenis_Mostoles", Arrays.asList(p3a,p3b));
		p3a.setActividad(a3); p3b.setActividad(a3); a3.getCentros().add(centro);
		
		PistaDeportiva p4 = new PistaDeportiva("Piscina olímpica",false);	
		Actividad a4 = new Actividad("Natacion_Mostoles", Arrays.asList(p4));
		p4.setActividad(a4); a4.getCentros().add(centro);
		
		PistaDeportiva p5 = new PistaDeportiva("Tatami reglamentario",false);	
		Actividad a5 = new Actividad("Judo_Mostoles", Arrays.asList(p5));
		p5.setActividad(a5); a5.getCentros().add(centro);
		
		servicioActividades.guardarActividades(Arrays.asList(a1,a2,a3,a4,a5));
		servicioPistas.guardarPistas(Arrays.asList(p1,p2,p3a,p3b,p4,p5));
		
		/*List<Actividad> act = new LinkedList<>();
		act.add(new Actividad("Football_Mostoles"));
		act.add(new Actividad("Baloncesto_Mostoles"));
		act.add(new Actividad("Tenis_Mostoles"));
		act.add(new Actividad("Natacion_Mostoles"));
		act.add(new Actividad("Judo_Mostoles"));
		
		for(Actividad a: act) a.getCentros().add(centro);
		servicioActividades.guardarActividades(act);*/	
	}
	
	public void setActividadesAlcorcon(CentroDeportivo centro)
	{	
		List<Actividad> act = new LinkedList<>();
		act.add(new Actividad("Football_Alcorcon"));
		act.add(new Actividad("Balonmano_Alcorcon"));
		act.add(new Actividad("Judo_Alcorcon"));
		act.add(new Actividad("Padel_Alcorcon"));
		
		for(Actividad a: act) a.getCentros().add(centro);
		servicioActividades.guardarActividades(act);
	}
	
	public void setActividadesFuenlabrada(CentroDeportivo centro)
	{	
		List<Actividad> act = new LinkedList<>();
		act.add(new Actividad("Football_Fuenla"));
		act.add(new Actividad("Baloncesto_Fuenla"));
		act.add(new Actividad("Tenis_Fuenla"));
		act.add(new Actividad("Balonmano_Fuenla"));
		act.add(new Actividad("Hockey_Fuenla"));
		
		for(Actividad a: act) a.getCentros().add(centro);
		servicioActividades.guardarActividades(act);
	}
	
	public void setActividadesAranjuez(CentroDeportivo centro)
	{		
		List<Actividad> act = new LinkedList<>();
		act.add(new Actividad("Football_Aranjuez"));
		act.add(new Actividad("Baloncesto_Aranjuez"));
		act.add(new Actividad("Tenis_Aranjuez"));
		act.add(new Actividad("Natacion_Aranjuez"));
		
		for(Actividad a: act) a.getCentros().add(centro);
		servicioActividades.guardarActividades(act);
	}
	
	public void setActividadesVicalvaro(CentroDeportivo centro)
	{		
		List<Actividad> act = new LinkedList<>();
		act.add(new Actividad("Football_Vicalvaro"));
		act.add(new Actividad("Judo_Vicalvaro"));
		act.add(new Actividad("Padel_Vicalvaro"));
		
		for(Actividad a: act) a.getCentros().add(centro);
		servicioActividades.guardarActividades(act);
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
