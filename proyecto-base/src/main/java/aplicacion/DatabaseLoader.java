package aplicacion;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader 
{	
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	@Autowired
	private RepositorioCentros repositorioCentros;
	
	@Autowired
	private ServicioActividades servicioActividades;	
	
	@Autowired
	private ServicioPistas servicioPistas;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct 
	private void initDatabase() 
	{
		repositorioUsuarios.save(new Usuario("Jorge",passwordEncoder.encode("1234"),"j.molinat.2017@alumnos.urjc.es","USER"));
		repositorioUsuarios.save(new Usuario("q",passwordEncoder.encode("w"),"e","ADMIN"));
		
		CentroDeportivo centro1 = new CentroDeportivo("Mostoles");		
		repositorioCentros.save(centro1);
	    setActividadesMostoles(centro1);
	   
	    CentroDeportivo centro2 = new CentroDeportivo("Alcorcón");		
	    repositorioCentros.save(centro2);
	    setActividadesAlcorcon(centro2);
	    
	    CentroDeportivo centro3 = new CentroDeportivo("Fuenlabrada");		
	    repositorioCentros.save(centro3);
	    setActividadesFuenlabrada(centro3);
	    
	    CentroDeportivo centro4 = new CentroDeportivo("Aranjuez");		
	    repositorioCentros.save(centro4);
	    setActividadesAranjuez(centro4);
	    
	    CentroDeportivo centro5 = new CentroDeportivo("Vicálvaro");		
	    repositorioCentros.save(centro5);
	    setActividadesVicalvaro(centro5);
	}
	
	private void setActividadesMostoles(CentroDeportivo centro)
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
	
	private void setActividadesAlcorcon(CentroDeportivo centro)
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
	
	private void setActividadesFuenlabrada(CentroDeportivo centro)
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
	
	private void setActividadesAranjuez(CentroDeportivo centro)
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
	
	private void setActividadesVicalvaro(CentroDeportivo centro)
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
	}
}
