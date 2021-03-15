package aplicacion;

import java.util.Arrays;
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
	
	@Autowired
	private ServicioPistas servicioPistas;
	/*
	PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
	Actividad a1 = new Actividad("Fútbol",Arrays.asList(p1));
	
	PistaDeportiva p2 = new PistaDeportiva("Cancha de baloncesto",false);	
	Actividad a2 = new Actividad("Baloncesto", Arrays.asList(p2));
	
	PistaDeportiva p3a = new PistaDeportiva("Pista de tenis A",false);	
	PistaDeportiva p3b = new PistaDeportiva("Pista de tenis B",false);	
	Actividad a3 = new Actividad("Tenis", Arrays.asList(p3a,p3b));
	
	PistaDeportiva p4 = new PistaDeportiva("Piscina olímpica",false);	
	Actividad a4 = new Actividad("Natacion", Arrays.asList(p4));
	
	PistaDeportiva p5 = new PistaDeportiva("Tatami reglamentario",false);	
	Actividad a5 = new Actividad("Judo", Arrays.asList(p5));
	
	PistaDeportiva p6 = new PistaDeportiva("Pista de padel",false);	
	Actividad a6 = new Actividad("Padel", Arrays.asList(p6));
	
	PistaDeportiva p7 = new PistaDeportiva("Sala de hockey",false);	
	Actividad a7 = new Actividad("Hockey sala", Arrays.asList(p7));
	
	PistaDeportiva p8 = new PistaDeportiva("Cancha de balonmano",false);	
	Actividad a8 = new Actividad("Balonmano",Arrays.asList(p8));*/
	
	/*@PostConstruct
	public void Init()
	{   
		//p1.setActividad(a1); p2.setActividad(a2); p3a.setActividad(a3); p3b.setActividad(a3); p4.setActividad(a4); p5.setActividad(a5); p6.setActividad(a6); p7.setActividad(a7); p8.setActividad(a8);
		
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
	    
	    //servicioActividades.guardarActividades(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8));
		//servicioPistas.guardarPistas(Arrays.asList(p1,p2,p3a,p3b,p4,p5,p6,p7,p8));
	}*/
	
	/*public void setActividadesCampus(CentroDeportivo centro)
	{	
		switch (centro.getCampus())
		{
		     case "Mostoles": a1.getCentros().add(centro); a2.getCentros().add(centro); a3.getCentros().add(centro); a4.getCentros().add(centro); a5.getCentros().add(centro); break;
		     case "Alcorcón": a2.getCentros().add(centro); a1.getCentros().add(centro); a6.getCentros().add(centro); a8.getCentros().add(centro); break;
		     case "Fuenlabrada": a1.getCentros().add(centro); a8.getCentros().add(centro); a7.getCentros().add(centro); a3.getCentros().add(centro); a2.getCentros().add(centro); break;
		     case "Aranjuez": a4.getCentros().add(centro); a2.getCentros().add(centro); a1.getCentros().add(centro); a3.getCentros().add(centro); break;
		     case "Vicálvaro": a1.getCentros().add(centro); a6.getCentros().add(centro); a5.getCentros().add(centro); break;	
		}
	}*/
		
	/*public void setActividadesMostoles(CentroDeportivo centro)
	{	
		PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
		Actividad a1 = new Actividad("Fútbol",Arrays.asList(p1));
		p1.setActividad(a1);  a1.getCentros().add(centro);	
		
		PistaDeportiva p2 = new PistaDeportiva("Cancha de baloncesto",false);	
		Actividad a2 = new Actividad("Baloncesto", Arrays.asList(p2));
		p2.setActividad(a2);  a2.getCentros().add(centro);
		
		PistaDeportiva p3a = new PistaDeportiva("Pista de tenis A",false);	
		PistaDeportiva p3b = new PistaDeportiva("Pista de tenis B",false);	
		Actividad a3 = new Actividad("Tenis", Arrays.asList(p3a,p3b));
		p3a.setActividad(a3); p3b.setActividad(a3); a3.getCentros().add(centro);
		
		PistaDeportiva p4 = new PistaDeportiva("Piscina olímpica",false);	
		Actividad a4 = new Actividad("Natacion", Arrays.asList(p4));
		p4.setActividad(a4);  a4.getCentros().add(centro);
		
		PistaDeportiva p5 = new PistaDeportiva("Tatami reglamentario",false);	
		Actividad a5 = new Actividad("Judo", Arrays.asList(p5));
		p5.setActividad(a5);  a5.getCentros().add(centro);
		
		servicioActividades.guardarActividades(Arrays.asList(a1,a2,a3,a4,a5));
		servicioPistas.guardarPistas(Arrays.asList(p1,p2,p3a,p3b,p4,p5));	
	}
	
	public void setActividadesAlcorcon(CentroDeportivo centro)
	{	
		PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
		Actividad a1 = new Actividad("Fútbol",Arrays.asList(p1));
		p1.setActividad(a1);  a1.getCentros().add(centro);	
		
		PistaDeportiva p2 = new PistaDeportiva("Cancha de baloncesto",false);	
		Actividad a2 = new Actividad("Baloncesto", Arrays.asList(p2));
		p2.setActividad(a2);  a2.getCentros().add(centro);
		
		PistaDeportiva p6 = new PistaDeportiva("Pista de padel",false);	
		Actividad a6 = new Actividad("Padel", Arrays.asList(p6));
		p6.setActividad(a6); a6.getCentros().add(centro);
		
		PistaDeportiva p8 = new PistaDeportiva("Cancha de balonmano",false);	
		Actividad a8 = new Actividad("Balonmano",Arrays.asList(p8));
		p8.setActividad(a8); a8.getCentros().add(centro);
		
		servicioActividades.guardarActividades(Arrays.asList(a1,a2,a6,a8));
		servicioPistas.guardarPistas(Arrays.asList(p1,p2,p6,p8));	
	}
	
	public void setActividadesFuenlabrada(CentroDeportivo centro)
	{	
		PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
		Actividad a1 = new Actividad("Fútbol",Arrays.asList(p1));
		p1.setActividad(a1);  a1.getCentros().add(centro);	
		
		PistaDeportiva p2 = new PistaDeportiva("Cancha de baloncesto",false);	
		Actividad a2 = new Actividad("Baloncesto", Arrays.asList(p2));
		p2.setActividad(a2);  a2.getCentros().add(centro);
		
		PistaDeportiva p3a = new PistaDeportiva("Pista de tenis A",false);	
		PistaDeportiva p3b = new PistaDeportiva("Pista de tenis B",false);	
		Actividad a3 = new Actividad("Tenis", Arrays.asList(p3a,p3b));
		p3a.setActividad(a3); p3b.setActividad(a3); a3.getCentros().add(centro);
		
		PistaDeportiva p7 = new PistaDeportiva("Sala de hockey",false);	
		Actividad a7 = new Actividad("Hockey sala", Arrays.asList(p7));
		p7.setActividad(a7); a7.getCentros().add(centro);
		
		PistaDeportiva p8 = new PistaDeportiva("Cancha de balonmano",false);	
		Actividad a8 = new Actividad("Balonmano",Arrays.asList(p8));
		p8.setActividad(a8); a8.getCentros().add(centro);
		
		servicioActividades.guardarActividades(Arrays.asList(a1,a2,a3,a7,a8));
		servicioPistas.guardarPistas(Arrays.asList(p1,p2,p3a,p3b,p7,p8));
	}
	
	public void setActividadesAranjuez(CentroDeportivo centro)
	{		
		PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
		Actividad a1 = new Actividad("Fútbol",Arrays.asList(p1));
		p1.setActividad(a1);  a1.getCentros().add(centro);	
		
		PistaDeportiva p2 = new PistaDeportiva("Cancha de baloncesto",false);	
		Actividad a2 = new Actividad("Baloncesto", Arrays.asList(p2));
		p2.setActividad(a2);  a2.getCentros().add(centro);
		
		PistaDeportiva p3a = new PistaDeportiva("Pista de tenis A",false);	
		PistaDeportiva p3b = new PistaDeportiva("Pista de tenis B",false);	
		Actividad a3 = new Actividad("Tenis", Arrays.asList(p3a,p3b));
		p3a.setActividad(a3); p3b.setActividad(a3); a3.getCentros().add(centro);
		
		PistaDeportiva p4 = new PistaDeportiva("Piscina olímpica",false);	
		Actividad a4 = new Actividad("Natacion", Arrays.asList(p4));
		p4.setActividad(a4);  a4.getCentros().add(centro);
		
		servicioActividades.guardarActividades(Arrays.asList(a1,a2,a3,a4));
		servicioPistas.guardarPistas(Arrays.asList(p1,p2,p3a,p3b,p4));
	}
	
	public void setActividadesVicalvaro(CentroDeportivo centro)
	{	
		PistaDeportiva p1 = new PistaDeportiva("Campo de Futbol",false);	
		Actividad a1 = new Actividad("Fútbol",Arrays.asList(p1));
		p1.setActividad(a1);  a1.getCentros().add(centro);	
		
		PistaDeportiva p5 = new PistaDeportiva("Tatami reglamentario",false);	
		Actividad a5 = new Actividad("Judo", Arrays.asList(p5));
		p5.setActividad(a5);  a5.getCentros().add(centro);
		
		PistaDeportiva p6 = new PistaDeportiva("Pista de padel",false);	
		Actividad a6 = new Actividad("Padel", Arrays.asList(p6));
		p6.setActividad(a6); a6.getCentros().add(centro);
		
		servicioActividades.guardarActividades(Arrays.asList(a1,a5,a6));
		servicioPistas.guardarPistas(Arrays.asList(p1,p5,p6));
	}*/
	
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
